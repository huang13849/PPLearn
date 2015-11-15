<!DOCTYPE html>
<html lang="en">
    <head>
		<#include "/head.ftl">
		<script language="javascript"> 
		function userVerify()
		{
			var usrName = document.getElementById("form-userAccount").value;
			obj s = usrName;
 			var i=1; 
 			alert(s);
 	
			} 
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
                            <h1>Welcome<strong>PP Learn</strong></h1>
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
			                        	<input type="text" name="userId" placeholder="Enter Your User Name..." class="form-control" id="form-userAccount"  onblur="return userVerify()" >
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="password">Password</label>
			                        	<input type="password" name="password" placeholder="Enter Your Password..." class="form-control" id="form-password">
			                        </div>
			                        <dd><span>Verify code: 
			                        	<label class="sr-only" for="userAccount">Verify code</label>
  										<input name="vc" type="text" id="vc"   size="5" onblur="checkcode()" />  
  											<img src="/assets/images/2907.jpg" style="cursor:pointer " onclick="changecode()" width="37" height="16" /></span><span class="reg1" id="sps5">Please type verify code</span><font id="mycode" style="display:none ">d</font> 
									</dd> 
			                        <button type="submit" class="btn">Sign in!</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>            
        </div>


		<#include "/js_resource.ftl">
    </body>
</html>