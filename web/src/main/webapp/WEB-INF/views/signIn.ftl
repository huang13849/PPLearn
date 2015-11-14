<!DOCTYPE html>
<html lang="en">
    <head>
		<#include "/head.ftl">
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
                            <h1><strong>PP Learn</strong> Welcome </h1>
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
			                        	<input type="text" name="userId" placeholder="Pick a User Name..." class="form-control" id="form-userAccount">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="password">Password</label>
			                        	<input type="text" name="password" placeholder="Create a Password..." class="form-control" id="form-password">
			                        </div>
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