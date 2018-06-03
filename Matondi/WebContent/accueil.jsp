<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Oleo+Script:400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Teko:400,700" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>accueil</title>
</head>
<body>
<section id="contact">
    <div class="container-fluid">
        <div class="section-content">
	        <div class="row">
	            <h1 class="section-header col-xs-12 col-sm-12 col-md-12 col-lg-12"><span class="content-header wow fadeIn " data-wow-delay="0.2s" data-wow-duration="2s">Organisation Matondi</span></h1>
	        </div>
            <div class="row">
                      <h1 class="col-xs-12 col-sm-12 col-md-12 col-lg-12">Bienvenue</h1>
            </div>
        </div>
        <div class="contact-section">
	        <div class="container">		    
			    <div class="row">
			          <form method="get" action="identification" class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
			              <button type="submit" class="btn btn-primary">Espace Organisateur</button>
			              <br/>
			              <br/>
			          </form>
			    </div>
			    <div class="row">
                     <h2 class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">Nos événements</h2>
                </div>
			    <div class="contact-section">
				    <%@ page import ="type.Evenement"%>
				    <% 
				        int nbrEvent = (int)session.getAttribute("nbrEventToDisplay");
				        for(int i=0 ; i<nbrEvent ; i++){
				            Evenement event = (Evenement)session.getAttribute("eventToDisplay"+i);
				    %>
				    <div class="row">
				        <form method="get" action="presentationEvenement" class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
				            <div class="form-group">
					            <input type="radio" style="display:none" name="radio" value="<%=event.getId() %>" checked="checked"></input>
					            <button type="submit" name="submitbutton" value="<%=event.getId() %>" class="btn btn-default submit"> <%=event.getName()%> <br/>Date : <%=event.getDate()%></button>
				            </div>
				        </form>
				    </div>
				    <% } %>
			    </div>		      
	        </div>
        </div>
    </div>
</section>   
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
    
</body>
</html>