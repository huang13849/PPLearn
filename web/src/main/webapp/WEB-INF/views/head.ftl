	<#import "/spring.ftl" as sp />
	
	<#assign projContext="${springMacroRequestContext.getContextUrl('/')}">
	<#if projContext?ends_with('/')>
	    <#assign projContext="${projContext?substring(0,( projContext?length - 1))}">
	</#if>
	
	<#assign assets="${projContext}/assets">
	       	
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>PP Learn</title>
	
	<!-- CSS -->
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
	<link rel="stylesheet" href="${assets}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${assets}/bootstrap/css/hackthon.css">
	<link rel="stylesheet" href="${assets}/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${assets}/css/form-elements.css">
	<link rel="stylesheet" href="${assets}/css/style.css">
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	
	<!-- Favicon and touch icons -->
	<link rel="shortcut icon" href="assets/ico/favicon.png">
	<link rel="apple-touch-icon-precomposed" sizes="144x144" href="${assets}/ico/apple-touch-icon-144-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="114x114" href="${assets}/ico/apple-touch-icon-114-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="72x72" href="${assets}/ico/apple-touch-icon-72-precomposed.png">
	<link rel="apple-touch-icon-precomposed" href="${assets}/ico/apple-touch-icon-57-precomposed.png">
	