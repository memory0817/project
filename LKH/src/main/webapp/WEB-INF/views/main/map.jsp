<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="../index_top.jsp" flush="false" />
<script>
	$(function() {

		$("#viewmap").on("click", function() {
			window.open("http://map.daum.net/link/map/LKH Co!,35.5350544, 129.3108984");

		});


		$("#find").on("click", function() {
			window.open("http://map.daum.net/link/to/LKH Co!,35.5350544, 129.3108984");

		});

	});
</script>
<div style="padding-top: 200px">
	<table>
		<colgroup>
			<col width="13%">
			<col width="25%">
			<col width="2%">
			<col width="35%">
			<col width="5%">
		</colgroup>
		<thead>
			<tr>
				<th></th>
				<th scope="col">

					<div id="map" style="width: 800px; height: 500px;">
						<script type="text/javascript"
							src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2f59b6420e56012fa4e413b3b81d00a0"></script>
						<script>
							var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
							var options = { //지도를 생성할 때 필요한 기본 옵션
								center : new daum.maps.LatLng(35.5350544,
										129.3108984), //지도의 중심좌표.
								level : 3,
								scrollwheel : true
							//지도의 레벨(확대, 축소 정도)
							};

							var map = new daum.maps.Map(container, options); //지도 생성 및 객체 리턴

							// 마커가 표시될 위치입니다 
							var markerPosition = new daum.maps.LatLng(
									35.5350544, 129.3108984);

							// 마커를 생성합니다
							var marker = new daum.maps.Marker({
								position : markerPosition
							});

							// 마커가 지도 위에 표시되도록 설정합니다
							marker.setMap(map);

							//var iwContent = '<div style="padding:5px;">LKH Co <br></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
							iwPosition = new daum.maps.LatLng(33.450701,
									126.570667); //인포윈도우 표시 위치입니다

							// 인포윈도우를 생성합니다
							var infowindow = new daum.maps.InfoWindow({
								position : iwPosition,
								content : iwContent
							});

							// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
							infowindow.open(map, marker);
						</script>
					</div>
				</th>
				<th></th>
				<th scope="col">

					<h1 class="line-heading">LKH Corporation</h1>
					<h4>(주)LKH Co 울산광역시 남구 삼산로35번길 19</h4>
					<h4>전화번호 : 1644-1234</h4>
					<h4>팩스 : 052-435-1233</h4>
					<h4>오시는길 : 울산광역시 남부 경찰서 앞 위치</h4>
					<button class="btn btn-sm btn-outline-dark" id="find">길찾기</button>
					<button class="btn btn-sm btn-outline-dark" id="viewmap">큰지도보기</button>


				</th>
				<th></th>
			</tr>
		</thead>
		<tbody>


		</tbody>
	</table>
</div>