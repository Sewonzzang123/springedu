<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
/*.container-h {
	position: relative;
}

.player {
	position: sticky;
	top: 0;
	text-align: center;
	background-color: black;
	width: 100%;
	height: 400px;
	overflow: hidden;
}

.player video {
	min-width: 100%;
	min-height: 100%;
}

.overlay {
	posigion: absolute;
	top: 200px;
	z-index: 1;
}

.overlay h1 {
	color: white;
	text-align: center;
	font-size: 3em;
}*/
img {
	max-height: 300px;
}

.carousel-item {
	transition: transform 5s ease, opacity 2s ease-in-out;
}
</style>
<script>
	$(function() {//현재문서가 로딩되면 = 현재문서가  dom구축되면
		$('.carousel').carousel({
			interval : 5000
		});

		$('#btn1').on('click', function(e) {
			$('.carousel').carousel('cycle');
		});

		$('#btn2').on('click', function(e) {
			$('.carousel').carousel('pause');
		});

		$('#btn3').on('click', function(e) {
			$('.carousel').carousel('prev');
		});

		$('#btn4').on('click', function(e) {
			$('.carousel').carousel('next');
		});
	});
</script>
<header>
	<!-- <section class="player">
    <video controls src="${contextPath }/video/Sunrise.mp4"></video>
    </section>  
    </div class="overlay">
    <div>방문을 환영합니다.</div>-->
	<div class="container container-h">
		<div id="carouselExampleControls" class="carousel slide"
			data-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img
						src="https://cdn.pixabay.com/photo/2020/08/07/09/23/flower-5470156__340.jpg"
						class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img
						src="https://cdn.pixabay.com/photo/2020/07/20/06/42/english-bulldog-5422018__340.jpg"
						class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img
						src="https://cdn.pixabay.com/photo/2017/09/25/13/12/dog-2785074__340.jpg"
						class="d-block w-100" alt="...">
				</div>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleControls"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleControls"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
		<div>
			<button id="btn1">순환</button>
			<button id="btn2">정지</button>
			<button id="btn3">이전</button>
			<button id="btn4">다음</button>
		</div>
		<!-- https://cdn.pixabay.com/photo/2020/08/07/09/23/flower-5470156__340.jpg -->
		<!-- https://cdn.pixabay.com/photo/2020/07/20/06/42/english-bulldog-5422018__340.jpg-->
		<!-- https://cdn.pixabay.com/photo/2017/09/25/13/12/dog-2785074__340.jpg -->
</header>