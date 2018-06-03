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
    <title>Programmation groupe</title>
</head>
<body>
<section id="contact">
    <div class="container-fluid">
        <div class="section-content">
            <h1 class="section-header"><span class="content-header wow fadeIn " data-wow-delay="0.2s" data-wow-duration="2s">Organisation Matondi</span></h1>
            <h3>Programmer un groupe pour</h3>
        </div>
        <div class="container">
            <div class="row">
                   <form method="get" action="GererEvenement" class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-right ">
                        <button type="submit" class="btn btn-primary right">Retour</button>
                   </form>
            </div>
	    </div>
	    <div class="contact-section">
	       <div class="container">
		        <div class="row">
		              <h1>Ajouter un groupe</h1>
		        </div>

                <div class="row">
			        <%@ page import ="java.util.ArrayList"%>
			        <%@ page import ="type.Groupe"%>
			        <%      
			        int nbrBands = (int)session.getAttribute("nbrBands");
			        for(int i=0 ; i<nbrBands ; i++){
			            String str ="band"+i;
			            Groupe band = (Groupe)session.getAttribute(str);
			        %>
			        <div class="col-xs-12 col-sm-4 col-md-3 col-lg-3">
				        <form method="post" action="GererEvenement">
				        <div class="form-group border">
				            <label>Cout du deffraiement</label>
				            <input type="number" class="form-control" name="cost" value=0 ></input>
				            <input type="radio" style="display:none" name="radio" value="<%=band.getId() %>" checked="checked"></input>
				            <button type="submit" class="btn btn-default submit total"name="submitbutton" value="submitBand"><%=band.getName()%> <br/> <%=band.getStyle()%> <br/>  <%=band.getNbrPeople()%> personnes</button>
				        </div>
				        </form>
			        </div>
			        <% } %>
		        </div>
            </div>
        </div>
    </div>  
</section>


</body>
</html>