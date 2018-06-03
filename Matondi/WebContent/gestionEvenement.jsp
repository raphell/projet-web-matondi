<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script type="text/javascript" src="gestionEvent.js" ></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://fonts.googleapis.com/css?family=Oleo+Script:400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Teko:400,700" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>identification</title>
</head>
<body onload='displayDays(0,${nbrDayEvent })'>
<section id="contact">
    <div class="container-fluid">
        <div class="section-content">
            <h1 class="section-header"><span class="content-header wow fadeIn " data-wow-delay="0.2s" data-wow-duration="2s">Organisation Matondi</span></h1>
            <h1>Gestion de ${nameEvent } </h1>
        </div>
        <div class="container">
            <div class="row">
                   <form method="get" action="gestion" class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-right ">
                        <button type="submit" class="btn btn-primary border right">Retour</button>
                   </form>
            </div>
        </div>
        <div class="contact-section">
            <div class="container">
                 <form method="get" action="ModifyEvent" class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center ">
                     <button type="submit" class="btn btn-primary border text-center">Modifier les informations de cet événement</button>
                 </form>    
			    
			    <div id="info" >
                    <div class="row">
                         <h1>Informations générales</h1>
                    </div>
                    <div class="bigInfo">
                        <div class="row">
                           Le  ${dateEvent }
                        </div> 
                         <div class="row">
                           Durée  :  ${nbrDayEvent } jours
                        </div> 
                         <div class="row">
                           Adresse  :  ${streetNbrEvent }   ${streetEvent }  ${cityEvent }
                        </div> 
                    </div>
                </div>           
			    	    
			    
			    <%@ page import ="type.Jour"%>
			    <%@ page import ="type.Creneau"%>
			    <%@ page import ="type.Groupe"%>
			     <%@ page import ="DAO.GroupeDAO"%>
			    <div class="row">   
			        <%  int nbrDay = (int)session.getAttribute("nbrDayEvent");
			            for(int i=0 ; i<nbrDay ; i++){
			                Jour day = (Jour)session.getAttribute("day"+i);
			        %>
			            <button onclick="displayDays( <%=i%>,${nbrDayEvent })" class="btn btn-primary border col-xs-<%=12/nbrDay%> col-sm-<%=12/nbrDay%> col-md-<%=12/nbrDay%> col-lg-<%=12/nbrDay%>"> Jour <%=i+1%> <br/><%=day.getDateJour()%> </button>
			        <% } %>
			    </div> 
			    
			    
			    <%  for(int i=0 ; i<nbrDay ; i++){
			            String str ="day"+i;
			            Jour day = (Jour)session.getAttribute(str);
			    %>
			        <div id="day<%=i %>">
			            <div class="row">
			               <h2 class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> Jour <%=i+1 %> </h2>
			            </div>
			            <div class="row">
			                <form method="post" action="ProgrammerGroupe">
			                        <input type="radio" style="display:none" name="radioAddBand" value="<%=day.getIdJour()%>" checked="checked"></input>
			                        <input type="submit" class="btn btn-primary border" name="addBandToDay" value="Ajouter un groupe"></button>
			                </form>
			            </div>
			            <div class="row">
			               <h4>Line-up :</h4>
			            </div>
			           
			            <div class="row">
			                <table class="border">
			                   <tr>
			                       <th>Nom</th>
			                       <th>Style</th>
			                       <th>Horaire début</th>
			                       <th>Horaire fin</th>
			                       <th>Supprimer</th>
			                   </tr>
			                <%  
			                int nbCreneau = (int)session.getAttribute("nbrCreneau"+i);
			                for(int j=0 ; j<nbCreneau ; j++){
			                    Creneau creneau = (Creneau)session.getAttribute("creneau"+i+j);
			                %>
			                   <tr>
			                         <td><%=GroupeDAO.getBandById(creneau.getIdBand()).getName()  %></td>
			                         <td><%=creneau.getStyle()%></td>
			                         <td><%=creneau.gethBegin()%>h <%=creneau.getmBegin()%></td>
			                         <td><%=creneau.gethEnd()%>h <%=creneau.getmEnd()%></td>
			                         <td></td>
			                   </tr>                  
			                <% } %>
			                </table>
			            </div>
			            
			            <hr>
			            
			            <div class="row">
			               <h4>Artistes présents :</h4>
			            </div>
			            
			            <div class="row">
			                <table>
			                   <tr>
			                       <th>Nom</th>
			                       <th>Style</th>
			                       <th>Horaire début</th>
			                       <th>Horaire fin </th>
			                       <th>Valider <div class="errorMsg">${erreurCreneau}</div> </th>
			                   </tr>
			                <%  
			                int nbBand = (int)session.getAttribute("nbrBands"+i);
			                for(int j=0 ; j<nbBand ; j++){
			                    Groupe band = (Groupe)session.getAttribute("band"+i+j);
			                %>
			                    
			                       <tr>
			                           <td class="col-xs-3 col-sm-3 col-md-3 col-lg-3"><%=band.getName()%></td>
			                           <td class="col-xs-2 col-sm-2 col-md-2 col-lg-2"><%=band.getStyle()%></td>
			                           
			                               <form method="post" action="ChoisirCreneau">
			                               <td class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
			                                    <input type="number" name="hDeb" placeholder="heure"></input>
			
			                                    <input type="number" name="mDeb" placeholder="minute"></input>
			                               </td>
			                               <td class="col-xs-2 col-sm-2 col-md-2 col-lg-2">    
			                                    <input type="number" name="hFin" placeholder="heure"></input>
			
			                                    <input type="number" name="mFin" placeholder="minute"></input>
			                                </td>  
			                                <td class="col-xs-2 col-sm-2 col-md-2 col-lg-2">  
			                                    <input type="radio" style="display:none" name="idBandSchedule" value="<%=band.getId()%>" checked="checked"></input>
			                                    <input type="radio" style="display:none" name="idDaySchedule" value="<%=day.getIdJour()%>" checked="checked"></input>
			                                    <input type="submit" class="btn btn-primary border"name="bandToSchedule" value="Ajouter à la line-up"></button>
			                                </td>
			                               </form>
			                           </td>
			                      <% } %>
			                </table>     
			            </div>
			        </div>
			    <% } %> 
            </div>
        </div>
    </div>  
</section>





<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
      
      
    
</body>
</html>