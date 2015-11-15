<!DOCTYPE html>
<html lang="en">
    <head>
		<#include "/head.ftl">
		<script language="javascript"> 
		function registry()
		{
			window.location.href="registerPage.jhtm";
		}
		
		function userVerify()
		{
			
			var usrName = document.getElementById("form-userAccount").value;
 		
 		}
 	
		</script>		
    </head>

    <body>

		<!-- Top menu -->
		<#include "/sign_in_top_menu.ftl">

        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-7 text">
                            <h1>Welcome to <strong>PP Learn</strong></h1>
                            <div class="description">
                         
                            </div>
                      
                        </div>
                        <div class="col-sm-5 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>Sign up now</h3>
                            		<p>Please Sign In</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-pencil"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="login.jhtm" method="post" class="registration-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="userAccount">User Name</label>
			                        	<input type="text" name="userId" placeholder="Enter Your User Name..." class="form-control" id="form-userAccount" " >
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="password">Password</label>
			                        	<input type="password" name="password" placeholder="Enter Your Password..." class="hackthon-form" id="form-password">
			                        </div>
			                        <dd><span width:200px>Verify code: 
			                        	<label class="sr-only" for="userAccount">Verify code</label>
  										<input name="vc" type="text" id="vc" placeholder="Code"  size="5"  />  
  											<img src="assets/img/2907.jpg" style="cursor:pointer "  width="37" height="16" /></span><span class="reg1" id="sps5"></span><font id="mycode" style="display:none ">d</font> 
									</dd> 
			                        <button type="submit" class="btn">Sign in!</button>
			                        
			                        <button type="button" class="btn" onclick ="registry()">Sign up!</button>
			                        
			                    </form>
			                    			                         
			                    
			                    
			                    <div style="color:red">${errorMsg!}</div>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>            
        </div>


		<#include "/js_resource.ftl">
    </body>
</html>