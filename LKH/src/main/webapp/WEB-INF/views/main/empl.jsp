<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>Carousel Template · Bootstrap</title>

<!--  Bootstrap 시작-->
<link rel="stylesheet" href="/resources/bootstrap-4.2.1/css/bootstrap.css" />
<script src="/webjars/jquery/3.3.1/dist/jquery.min.js"></script>
<script src="/webjars/popper.js/1.14.6/dist/umd/popper.min.js"></script>
<script src="/resources/bootstrap-4.2.1/js/bootstrap.js"></script>
<!-- Bootstrap -->

<!-- Custom styles for this template -->
	<link
		href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900"
		rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="/resources/css/carousel.css" rel="stylesheet">
	<link
		href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:300,400,700|Noto+Serif+KR:300,400,700|Open+Sans:300i,400,400i,600,700,800&amp;subset=korean"
		rel="stylesheet">

<!-- <!— font awesome —> -->

<link rel="stylesheet" href="/webjars/font-awesome/5.6.3/css/all.css"/>



    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
  
  </head>
  <body>
    <header>
  <!-- <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="#">Carousel</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/visit/visitboard">Q&A</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="#">Disabled</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/login/loginForm">Login</a>
        </li>
      </ul>
      <form class="form-inline mt-2 mt-md-0">
        <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
      </form>
    </div>
  </nav> -->
  <div class="pos-f-t">
  <div class="collapse navbar-collapse" id="navbarToggleExternalContent">
    <div class="bg-dark p-4">
      <h5 class="text-white h4">Collapsed content</h5>
      <span class="text-muted">Toggleable via the navbar brand.</span>
      <span class="text-muted">Toggleable via the navbar brand.</span>
      <span class="text-muted">Toggleable via the navbar brand.</span>
      <span class="text-muted">Toggleable via the navbar brand.</span>
      <span class="text-muted">Toggleable via the navbar brand.</span>
      <span class="text-muted">Toggleable via the navbar brand.</span>
      <span class="text-muted">Toggleable via the navbar brand.</span>
      <span class="text-muted">Toggleable via the navbar brand.</span>
      <span class="text-muted">Toggleable via the navbar brand.</span>
      <p>dafasdasd</p>
      <p>dafasdasd</p>
      <p>dafasdasd</p>
      <p>dafasdasd</p>
      <p>dafasdasd</p>
      <p>dafasdasd</p>
      <p>dafasdasd</p>
      <p>dafasdasd</p>
      <p>dafasdasd</p>
    </div>
  </div>
  <nav class="navbar navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="text-right">
	    <a class="text-white"href="/login/loginForm">로그인</a>
    </div>
  </nav>
</div>
</header>
 
<main role="main">

    <div class="row featurette">
    <label class="col-md-5"></label>
      <div class="col-md-7">
      <h2 class="featurette-heading"> Join LKH . <span class="text-muted">채용공고.</span></h2>
      
      </div>
    </div>
    
    <hr class="featurette-divider">

	
		<!--  <video src="/resources/media/vid.mp4" width="100%" height="500"
			controls autoplay> 브라우저가 이 비디오 태그를 지원하지 않습니다.
		</video>  -->
		
		<iframe width="100%" height="500" src="https://www.youtube.com/embed/pNfTK39k55U" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" controls autoplay allowfullscreen></iframe>
	
	  <hr class="featurette-divider">
	  	
		<button type="button" class="btn btn-secondary btn-lg btn-block modify" data-memurl="#"><a href="/resources/file/text.txt" download>양식다운로드</a></button>

	  <hr class="featurette-divider">

    <div class="row featurette">
      <div class="col-md-7 order-md-2">
        <h2 class="featurette-heading">Oh yeah, it’s that good. <span class="text-muted">See for yourself.</span></h2>
        <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
      </div>
      <div class="col-md-5 order-md-1">
        <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 500x500"><title>Placeholder</title><rect fill="#eee" width="100%" height="100%"/><text fill="#aaa" dy=".3em" x="50%" y="50%">500x500</text></svg>
      </div>
    </div>
    

    <hr class="featurette-divider">

    <!-- /END THE FEATURETTES -->

  </div><!-- /.container -->


  <!-- FOOTER -->
  <footer class="container">
    <p class="float-right"><a href="#">Back to top</a></p>
    <p>&copy; 2017-2018 Company, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
    <p>&copy; 2017-2018 Company, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="admin/adminlogin">관리자</a></p>
  </footer>
</main>
</body>
</html>
