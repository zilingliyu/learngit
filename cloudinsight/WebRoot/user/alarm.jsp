<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <header id="myCarousel" class="carousel slide" data-interval="false" data-ride="carousel" ">
      <!-- Indicators -->
      <!-- Wrapper for slides -->
      <div class="carousel-inner">
        <div class="item active">
          <div class="fill" style="background-image: url('img/banner02.jpg');"></div>
        </div>
      </div>
    </header>
  
  <div class="content-section-b">
   <div class="container">
    <div class="row" style="background: #fff; margin-top: 5px;">

				<div class="col-lg-12">
					<div class="panel-group">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4>
									告警
								</h4>
							</div>
							<div class="panel-body">
							<form action="${pageContext.request.contextPath}/operator/getAlarm.do" method="post">
							<div class="input-group col-xs-3" style="float:right;">
									
                                 <input type="text" class="form-control input-sm" name="name" value="${s_host.name}" placeholder="请输入监控名称...."  >
                                 <span class="input-group-btn">
                                  <button class="btn btn-default btn-sm" type="submit">查询</button>
                                </span>
                                
                                 </div>
                              </form>
                              
                              </div>
							<table id="monitor" class="table table-bordered table-hover" style="font-size: 10px;">
								<thead>
									<tr>
										<th>
											序号
										</th>
										<th>
											监控名称
										</th>
										<th>
											告警信息
										</th>
										<th>
										时间
										</th>
										<th>
											级别
										</th>
										
									</tr>
								</thead>
								<c:forEach var="map" items="${list}" varStatus="status">
									
									<tbody>
										<tr>
											<td>
												${status.index+1}
											</td>
											<td>
												${map.host}
											</td>
											<td>
											${map.description}
											</td>
											<td>
											${map.time}
											</td>
											<td>
												${map.priority}
											</td>
										</tr>
									</tbody>

								</c:forEach>

							</table>
							<nav >
		                     <ul class="pagination">
		                    	${pageCode }
		                     </ul>
	                      </nav>
						</div>
					</div>
				</div>
			</div>
       </div>
       </div>