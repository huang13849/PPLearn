<!DOCTYPE html>
<html lang="en">
    <head>
		<#include "/head.ftl">
    </head>

    <body>

		<!-- Top menu -->
		<#include "/top_menu.ftl">

        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-7 text">
                            <h1><strong>PP Learn</strong> Sign Up</h1>
                            <div class="description">
                            	<p>
	                            	You must be over 18 to join!
                            	</p>
                            </div>
                            <div class="top-big-link">
                            	<a class="btn btn-link-1" href="#">Login</a>                            	
                            </div>
                        </div>
                        <div class="col-sm-5 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>Sign up now</h3>
                            		<p>Fill in the form below to get instant access:</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-pencil"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="register.jhtm" method="post" class="registration-form">
			                        <div class="form-group">
			                        	<label class="sr-only" for="email">Email</label>
			                        	<input type="text" name="email" placeholder="Email..." class="form-email form-control" id="form-email">
			                        </div>
			                    	<div class="form-group">
			                    		<label class="sr-only" for="userAccount">User Name</label>
			                        	<input type="text" name="userAccount" placeholder="Pick a User Name..." class="form-control" id="form-userAccount">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="password">Password</label>
			                        	<input type="text" name="password" placeholder="Create a Password..." class="form-control" id="form-password">
			                        </div>
  									<div class="form-group">
			                        	<label class="sr-only" for="offer">Password</label>
			                        	<input type="text" name="offer" placeholder="I can teach..." class="form-control" id="form-offer">
			                        </div>	
			                        <div class="form-group">
			                        	<label class="sr-only" for="want">Password</label>
			                        	<input type="text" name="want" placeholder="I want learn..." class="form-control" id="form-want">
			                        </div>	
			                        <div class="form-group">
			                        	<label class="sr-only" for="introduce">About yourself</label>
			                        	<textarea name="introduce" placeholder="About yourself..." 
			                        				class="form-about-yourself form-control" id="form-about-yourself"></textarea>
			                        </div>
			                        <button type="submit" class="btn">Sign me up!</button>
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