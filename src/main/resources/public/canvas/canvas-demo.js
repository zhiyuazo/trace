/**
 * 
 */
$(function(){
	//draw a rect...
	var c=document.getElementById("canvas-rect"); 
	var ctx=c.getContext("2d"); 
	ctx.fillStyle="#FF0000"; //可以是颜色，渐变，图案
	//(x,y,width,height) 
	// x,y 为起始位置
	// width,height 为内容宽高
	ctx.fillRect(0,0,150,75); 
	

	//draw a line...
	var c=document.getElementById("canvas-line");
	var ctx=c.getContext("2d");
	ctx.moveTo(0,0); //线条开始坐标
	ctx.lineTo(200,100); //线条结束坐标
	ctx.stroke(); //绘制

	//draw a circle...
	var c=document.getElementById("canvas-circle");
	var ctx=c.getContext("2d");
	ctx.beginPath();
//	arc(x,y,r,start,stop)
	ctx.arc(100,100,100,0,2*Math.PI);
	ctx.stroke();

	//draw a text...
	var c=document.getElementById("canvas-text");
	var ctx=c.getContext("2d");
	ctx.font="30px Arial";
	ctx.fillText("Hello World",10,50); //fillText(text,x,y)
	ctx.strokeText("Hello World",10,100); //fillText(text,x,y)

	 
	//draw LinearGradient...
	var c=document.getElementById("canvas-lineChange");
	var ctx=c.getContext("2d");
		// 创建线条渐变 createLinearGradient(x,y,x1,y1)
	var grd=ctx.createLinearGradient(0,0,200,0);
	grd.addColorStop(0,"red");
	grd.addColorStop(1,"white");
		// 填充渐变
	ctx.fillStyle=grd;
	ctx.fillRect(0,0,150,80);
	
	//draw RadialGradient...
	var c=document.getElementById("canvas-RadialChange");
	var ctx=c.getContext("2d");
		// 创建径向渐变createRadialGradient(x,y,r,x1,y1,r1) 
	var grd=ctx.createRadialGradient(75,50,5,90,60,100);
	grd.addColorStop(0,"red");
	grd.addColorStop(1,"white");
		// 填充渐变
	ctx.fillStyle=grd;
	ctx.fillRect(0,0,150,80);
	
	//draw image...
	var c=document.getElementById("canvas-image");
	var ctx=c.getContext("2d");
	var img=document.getElementById("pic");
	ctx.drawImage(img,10,10);

	
})