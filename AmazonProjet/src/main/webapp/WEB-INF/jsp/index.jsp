<jsp:include page="templates/header.jsp" />

<div id="carousel-example-generic" class="carousel slide replaceCarousel" data-ride="carousel">
	<!-- Indicators -->
	<ol class="carousel-indicators">
		<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
		<li data-target="#carousel-example-generic" data-slide-to="1"></li>
		<li data-target="#carousel-example-generic" data-slide-to="2"></li>
	</ol>

	<!-- Wrapper for slides -->
	<div class="carousel-inner" role="listbox">
		<div class="item active">
			<a href="/liste-toutes-les-musiques/MusiqueClassqie">
				<img src="/resources/img/home/music_classique.jpg" alt="music classique">
			</a>
		</div>

		<div class="item">
			<a href="/liste-toutes-les-musiques/VINYLE/single/AllYears/ALL">
				<img src="/resources/img/home/music_vinyle.jpg" alt="music vinyle">
			</a>
		</div>

		<div class="item">
			<a href="#">
				<img src="/resources/img/home/zootopie.jpg" alt="music vinyle">
			</a>
		</div>


	</div>

	<!-- Controls -->
	<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
		<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
		<span class="sr-only">Previous</span>
	</a>
	<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
		<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		<span class="sr-only">Next</span>
	</a>
</div>
<h1>Nozama</h1>

<jsp:include page="templates/footer.jsp" />