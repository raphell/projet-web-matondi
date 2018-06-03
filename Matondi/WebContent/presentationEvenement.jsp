<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="presentationEvent.js" type="text/javascript"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://fonts.googleapis.com/css?family=Oleo+Script:400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Teko:400,700" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="style.css">
	<title>Insert title here</title>
</head>

<body onload='displayDays(0,${nbrDayEventDisplay })'>


<section id="contact">
    <div class="container-fluid">
        <div class="section-content">
            <h1 class="section-header"><span class="content-header wow fadeIn " data-wow-delay="0.2s" data-wow-duration="2s">Organisation Matondi</span></h1>
            <h1>${nameEvent }</h1>
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
	                <button onclick="displayLineUp()" class="border btn btn-primary col-xs-6 col-sm-6 col-md-6 col-lg-6">Voir line-up</button>
	                <button onclick="displayInformations()" class="border btn btn-primary col-xs-6 col-sm-6 col-md-6 col-lg-6">Voir informations</button>
	            </div>
	            
	            <br/>
	            <div id="lineUp">
	                <div class="row">
	                    <%@ page import ="type.Creneau"%>
	                    <%@ page import ="type.Jour"%>
	                     <% int nbrDay = (int)session.getAttribute("nbrDayEventDisplay");
	                        for(int i=0 ; i<nbrDay ; i++){
	                            String str ="dayDisplay"+i;
	                            Jour day = (Jour)session.getAttribute(str);
	                    %>
	                        
	                            <button onclick="displayDays( <%=i%>,${nbrDayEventDisplay })" class="border btn btn-primary col-xs-<%=12/nbrDay%> col-sm-<%=12/nbrDay%> col-md-<%=12/nbrDay%> col-lg-<%=12/nbrDay%>"> Jour <%=i+1%>: <%=day.getDateJour()%> </button>
	                        
	                    <% } %>
	                </div>
	                <%  for(int i=0 ; i<nbrDay ; i++){
	                        String str ="dayDisplay"+i;
	                        Jour day = (Jour)session.getAttribute(str);
	                %>
	                    <div id="day<%=i %>">
	                        <div class="row">
	                            <h1 class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> Jour <%=i+1 %> </h1>
	                        </div>
	                        <div class="row">
	                            <h3 class="col-xs-12 col-sm-12 col-md-6 col-lg-6">Line-up :</h3>
	                        </div>
	                        <%  
	                        int nbCreneau = (int)session.getAttribute("nbrCreneauDisplay"+i);
	                        for(int j=0 ; j<nbCreneau ; j++){
	                            Creneau creneau = (Creneau)session.getAttribute("creneauDisplay"+i+j);
	                        %>
	                        <div class="row">
	                            <p class="col-xs-12 col-sm-12 col-md-6 col-lg-6" ><%=creneau.gethBegin()%>h<%=creneau.getmBegin()%>min - <%=creneau.gethEnd()%>h<%=creneau.getmEnd()%><br/><%=creneau.getStyle()%></p>
	                        </div>
	                        <% } %>
	                    </div>
	
	                <% } %>
	            </div>
	            
	            <div id="info" >
	                <div class="row">
	                     <h1>Informations générales</h1>
	                </div>
	                <div class="bigInfo">
		                <div class="row">
		                   Le  ${DateDisplay }
		                </div> 
		                 <div class="row">
	                       Durée  :  ${NbrDayDisplay } jours
	                    </div> 
	                     <div class="row">
	                       Adresse  :  ${streetNbrDisplay }   ${streetDisplay }  ${cityDisplay }
	                    </div> 
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