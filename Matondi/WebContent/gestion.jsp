<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="gestion.js" type="text/javascript"></script>
    <link href="https://fonts.googleapis.com/css?family=Oleo+Script:400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Teko:400,700" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="style.css">
	<title>Gestion</title>
</head>

<body onload='displayEventList()'>
<section id="contact">
    <div class="container-fluid">
        <div class="section-content">
            <h1 class="section-header"><span class="content-header wow fadeIn " data-wow-delay="0.2s" data-wow-duration="2s">Organisation Matondi</span></h1>
            <h1>centre de gestion</h1>
        </div>
        <div class="container">
            <div class="row">
                   <form method="get" action="accueil" class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-right ">
                        <button type="submit" class="btn btn-primary right">Accueil</button>
                   </form>
            </div>
        </div>
        <div class="contact-section">
            <div class="container">
            <div class="row">   
                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">      
                    <h2 class="text-center">Evénements</h2>
                    <form method="get" action="creationEvenement" class="horiz">
                        <button class="btn btn-primary total" type="submit">Créer un événement</button>
                    </form>
                    <button class="btn btn-primary total" onclick="displayEventList()">Voir un événement</button>
                    <br/>
                    <div id="eventDiv">
                        <div class="text-right">
                            <a class="btn btn-primary text-right" href="VoirTousEvenements">voir les anciens événements...</a>
                        </div>
                        <%@ page import ="java.util.ArrayList"%>
                        <%@ page import ="java.util.List"%>
                        <%@ page import ="type.Evenement"%>
                        <%      
                        int nbrEvent = (int)session.getAttribute("nbrEvent");
                        for(int i=0 ; i<nbrEvent ; i++){
                            String str ="incomingEvent"+i;
                            Evenement event = (Evenement)session.getAttribute(str);
                        %>
                        <form method="get" action="GererEvenement" class="text-right">
                            <input type="radio" style="display:none" name="radio" value="<%=event.getId() %>" checked="checked"></input>
                            <button class="btn btn-primary text-right" type="submit" name="submitbutton" value="<%=event.getId() %>"> <%=event.getName() %> le <%=event.getDate() %></button>
                        </form>
                        <% } %>
                        
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                    <h2 class="text-center">Groupes</h2>
                    <form method="get" action="creationGroupe" class="horiz">
                        <button class="btn btn-primary" type="submit">Enregistrer un groupe</button>
                    </form>
                    <form method="get" action="VoirGroupe" class="horiz">
                        <button class="btn btn-primary" type="submit">Voir les groupes</button>
                    </form>
                </div>
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