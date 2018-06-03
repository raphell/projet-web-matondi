<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <title>Voir groupes</title>
</head>
<body>
<section id="contact">
    <div class="container-fluid">
        <div class="section-content">
            <h1 class="section-header"><span class="content-header wow fadeIn " data-wow-delay="0.2s" data-wow-duration="2s">Organisation Matondi</span></h1>
            <h1>Tous les groupes</h1>
        </div>
        <div class="container">
            <div class="row">
                   <form method="get" action="gestion" class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-right ">
                        <button type="submit" class="btn btn-primary right">Retour</button>
                   </form>
            </div>
        </div>
        <div class="contact-section">
            <div class="container">
	            <div class="row">
	                <strong>Cliquer sur un groupe pour modifier ses informations</strong>
	            </div>
	            <br/>
                <div class="row">
		            <%@ page import ="java.util.ArrayList"%>
		            <%@ page import ="type.Groupe"%>
		            <%      
		            int nbrBands = (int)session.getAttribute("nbrAllBands");
		            for(int i=0 ; i<nbrBands ; i++){
		                String str ="allBand"+i;
		                Groupe band = (Groupe)session.getAttribute(str);
		            %>
		            <form method="get" action="GererGroupe" class="col-xs-12 col-sm-4 col-md-3 col-lg-3 horiz">
		                <div class="form-group">
			                <input type="radio" style="display:none" name="radio" value="<%=band.getId() %>" checked="checked"></input>
			                <button type="submit" class="btn btn-default submit" name="submitbutton" value="<%=band.getId() %>"><%=band.getName() %><br/><%=band.getStyle()%> <br/><%=band.getNbrPeople()%> membres <br/><%=band.getMail()%><br/><%=band.getPhone()%></button>
		                </div>
		            </form>
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