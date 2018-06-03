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
    <title>Creation Evenement</title>
</head>
<body>
<section id="contact">
    <div class="container-fluid">
        <div class="section-content">
            <h1 class="section-header"><span class="content-header wow fadeIn " data-wow-delay="0.2s" data-wow-duration="2s">Organisation Matondi</span></h1>
            <h1>Création d'un événement</h1>
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
		            <form method="Post" action="creationEvenement" class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
		                <div class="form-group">
			                <label for="nameEvent">Nom</label>
			                <input type="text" class="form-control" id="nameEvent" name="nameEvent"/>
		                </div>
		                <div class="form-group">
			                <label for="cityEvent">Ville</label>
			                <input type="text" class="form-control" id="cityEvent" name="cityEvent"/>
		                </div>
		                <div>${errorCreateEvent}</div>
		                
		                <div class="form-group">
			                <label for="streetEvenement">rue</label>
			                <input type="text" class="form-control" id="streetEvent" name="streetEvenement"/>
		                </div>
		                <div class="form-group">
			                <label for="streetNumberEvent">numero rue</label>
			                <input type="number" class="form-control" id="streetNumberEvent" name="streetNumberEvent"/>
		                </div>
		                <div class="form-group">
			                <label for="dateEvent">date</label>
			                <input type='date' class="form-control" id="dateEvent" name="dateEvent" placeholder="yyyy-mm-dd"/>
		                </div>
		                <div class="form-group">
			                <label for="nbrDayEvent">nbr jours</label>
			                <input type="number" class="form-control" id="nbrDayEvent" name="nbrDayEvent"/>
		                </div>
		                <input type="submit" class="btn btn-default submit text-center"/>
		            </form>
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