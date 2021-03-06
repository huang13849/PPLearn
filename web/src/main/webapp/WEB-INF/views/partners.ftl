<!DOCTYPE html>
<html>
<head><#include "/head.ftl">
</head>
<body>

	<div class="section-preview">
		<div class="container">
			<div class="row" style="margin-bottom: 20px; margin-left: 0px;">
				<p>
					<span class="group_day"><i class="fa fa-star"
						style="color: #f39c12;"></i> Learning</span> <span class="group_date">Partners</span>
				</p>
			</div>
			
			
    		
			<div class="row">
			<#assign userCount=0>
    		<#list users as u>
				<div class="col-lg-4 col-sm-6 col-md-4>
					<div class="preview">
						<div align ="center" class="image">
							<a href="javascript:void(0);" target="_blank" rel="nofollow"
								onclick="_gaq.push(['_trackEvent', 'Click', 'trbble']);"> 
								<img height="160px" width="160px" 
								src="${projContext}/${u.headImageUrl!}" class="img-responsive"
								alt="" >
							</a>
						</div>
						
						<div class="options">
						
							<div style="color:white"> ${u.nickName!}</div>
							<div style="color:white"> ${u.introduce!}</div>
							<div class="btn-group">
								<a class="btn btn-success" href="${projContext}/chatPage.jhtm?toUserMail=${u.email!}"
									target="_blank" rel="nofollow"
									onclick="_gaq.push(['_trackEvent', 'Click', 'trbble']);">Hi
									&nbsp;&nbsp;<i class="fa fa-external-link"></i>
								</a>
							</div>
						</div>
					</div>
				<#assign userCount=(userCount + 1)>
    		</#list>
    		</div>
			</div>
			
		</div>
	</div>

	<#include "/js_resource.ftl">
</body>
</html>
