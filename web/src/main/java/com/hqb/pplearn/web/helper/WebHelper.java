package com.hqb.pplearn.web.helper;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.hqb.pplearn.biz.model.User;
import com.hqb.pplearn.common.util.excel.ExcelUtil;

public class WebHelper {

	private static final String SESSION_KEY_LOGIN_USER = "loginUser";
	private static final Logger LOG = LoggerFactory.getLogger(WebHelper.class);
	private static final int BUFFER_SIZE = 4096;

	public static final String SMALL_THUMBNAIL_POSTFIX = "_small";

	public static void download(HttpServletRequest request, HttpServletResponse response, FileInputStream inputStream, int contentLength, String fileName) throws IOException {
		// get MIME type of the file
		String mimeType = "application/octet-stream";
		System.out.println("MIME type: " + mimeType);

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength(contentLength);

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", fileName);
		response.setHeader(headerKey, headerValue);

		// get output stream of the response
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();
	}

	public static void writeImage2HttpResponse(HttpServletResponse response, byte[] imageBytes) {
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");

		try {
			ByteArrayInputStream in = new ByteArrayInputStream(imageBytes); // 将b作为输入流；

			BufferedImage image = ImageIO.read(in);
			ServletOutputStream out = response.getOutputStream();
			// write the data out
			ImageIO.write(image, "jpg", out);
			try {
				out.flush();
			} finally {
				out.close();
			}
		} catch (Exception ex) {
			LOG.error(ex.getMessage(), ex);
		}
	}

	public static void writeList2Excel(HttpServletResponse response, List<?> doctors, String[] headers, String[] fieldNames, String fileName) {
		try {
			ExcelUtil.exportExcel(headers, fieldNames, doctors, response.getOutputStream(), "yyyy-MM-dd");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getXmlFromRequestBody(HttpServletRequest request) throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader bufferedReader = null;

		try {
			bufferedReader = request.getReader();
			char[] charBuffer = new char[128];
			int bytesRead;
			while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
				sb.append(charBuffer, 0, bytesRead);
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}
		return sb.toString();
	}

	public static void writeXmlToResponse(HttpServletResponse response, String responseBody) {
		// 设置返回header
		response.setHeader("Status-Code", "HTTP/1.1 200 OK");
		response.setHeader("Date", new Date() + "");
		response.setHeader("Content-Length", responseBody.length() + "");
		try {
			// 输出 ，返回到客户端
			OutputStream opt = response.getOutputStream();
			OutputStreamWriter out = new OutputStreamWriter(opt);
			out.write(responseBody);
			out.flush();
		} catch (IOException e) {
			LOG.error(" *** IOException ***", e);
		}
	}

	public static void writeBytes2File(String fileFullName, byte[] photoBytes) throws IOException {
		File imageFile = new File(fileFullName);
		if (imageFile.exists()) {
			FileUtils.deleteQuietly(imageFile);
		}
		FileUtils.writeByteArrayToFile(imageFile, photoBytes);
	}

	public static String writeImage2File(String appRootPath, String relatedPhtotPath, String imageFileName, byte[] fileBytes) throws IOException {
		return writeImage2File(appRootPath, relatedPhtotPath, imageFileName, fileBytes, false);
	}

	public static String writeImage2File(String appRootPath, String relatedPhtotPath, String imageFileName, byte[] fileBytes, boolean withThumbnail) throws IOException {
		String fileExtensionName = "jpg";
		String originalImageName = write2file(appRootPath, relatedPhtotPath, imageFileName, fileBytes, fileExtensionName);
		if (withThumbnail) {
			ByteArrayInputStream in = new ByteArrayInputStream(fileBytes);
			String relatedName = generateRelatedName(relatedPhtotPath, imageFileName, fileExtensionName, SMALL_THUMBNAIL_POSTFIX);
			String fullName = String.format("%s/%s", appRootPath, relatedName);
			Thumbnails.of(in).size(100, 100).toFile(fullName);
		}
		return originalImageName;
	}

	public static String write3gpToFile(String appRootPath, String relatedPhtotPath, String imageFileName, byte[] fileBytes, String originName, String originFileName)
	        throws IOException {
		String fileExtensionName = getFileExtensionName(originName, originFileName);
		return write2file(appRootPath, relatedPhtotPath, imageFileName, fileBytes, fileExtensionName);
	}

	private static String getFileExtensionName(String originName, String originFileName) {
		String fileExtensionName = StringUtils.EMPTY;

		try {
			if (StringUtils.isNotBlank(originFileName) && StringUtils.contains(originFileName, ".")) {
				originFileName = StringUtils.trim(originFileName);
				int lastIndex = StringUtils.lastIndexOf(originFileName, ".");
				fileExtensionName = originFileName.substring(lastIndex + 1);
			} else if (StringUtils.isNotBlank(originName) && StringUtils.contains(originName, ".")) {
				originName = StringUtils.trim(originName);
				int lastIndex = StringUtils.lastIndexOf(originName, ".");
				fileExtensionName = originFileName.substring(lastIndex + 1);
			}
		} catch (Exception ex) {
			LOG.error(ex.getMessage(), ex);
		}

		if (StringUtils.isBlank(fileExtensionName)) {
			fileExtensionName = "unknow";
		}
		return fileExtensionName;
	}

	private static String write2file(String appRootPath, String relatedPhtotPath, String fileName, byte[] fileBytes, String fileExtensionName) throws IOException {
		String relatedName = null;
		if (fileBytes != null && fileBytes.length > 0) {
			relatedName = generateRelatedName(relatedPhtotPath, fileName, fileExtensionName, null);
			String fullName = String.format("%s/%s", appRootPath, relatedName);
			WebHelper.writeBytes2File(fullName, fileBytes);
		}
		return relatedName;
	}

	private static String generateRelatedName(String relatedPhtotPath, String fileName, String fileExtensionName, String suffix) {
		fileName = StringUtils.replace(fileName, "-", "");
		fileName = String.format("%s/%s.%s", relatedPhtotPath, fileName, fileExtensionName);

		if (suffix != null)
			fileName = String.format("%s%s.%s", fileName, suffix, fileExtensionName);
		return fileName;
	}

	public static String getIpAddr(final HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String buildErrorMsg(BindingResult result) {
		StringBuilder errMsgBuilder = new StringBuilder();
		if (result != null && result.hasErrors()) {
			for (FieldError err : result.getFieldErrors()) {
				if (errMsgBuilder.length() > 0) {
					errMsgBuilder.append(" ; ");
				}
				errMsgBuilder.append(err.getDefaultMessage());
			}
		}
		return errMsgBuilder.toString();
	}

	public static void saveCurrentUserToSeesion(HttpServletRequest request, User loginUser) {
		HttpSession session = request.getSession(true);
		session.setAttribute(SESSION_KEY_LOGIN_USER, loginUser);
	}

	public static void removeCurrentUserFromSeesion(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			// session.invalidate();
			session.removeAttribute(SESSION_KEY_LOGIN_USER);
		}
	}

	public static User getCurrentUserFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return (User) session.getAttribute(SESSION_KEY_LOGIN_USER);
		} else {
			return null;
		}
	}
}
