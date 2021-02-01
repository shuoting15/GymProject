/**
 * 
 */

const canvas = document.getElementById("canvas");
const ctx = canvas.getContext("2d");

const list = [
  { name: "再來一次", pct: 0.2 },
  { name: "三千折百", pct: 0.2 },
  { name: "滿千折20", pct: 0.2 },
  { name: "三千折百", pct: 0.2 },
  { name: "滿千折20", pct: 0.2 }
 
];
const colorList = ["#336666", "#4F9D9D","#81C0C0", "#4F9D9D","#81C0C0"];

class Turntable {
  constructor({
    canvas,
    ctx,
    list,
    colorList,
    radius,
    lineWidth,
    paddingTop,
    fontSize,
    initSpeed,
    constSpeed,
    autoStop
  }) {
    this.canvas = canvas;
    this.ctx = ctx;
    this.list = list;
    this.colorList = colorList;
    this.radius = radius;
    this.lineWidth = lineWidth;
    this.paddingTop = paddingTop;
    this.fontSize = fontSize;
    this.initSpeed = initSpeed;
    this.constSpeed = constSpeed;
    this.autoStop = autoStop;
    this.speed = this.initSpeed;
    this.timer = 0;
    this.rotating = false;
    this.raf = null;
  }

  draw() {
    console.log(this.timer);
    if (this.rotating && this.autoStop) {
      this.timer = (this.timer + this.speed) % 1000;
      if (this.speed > 0.1) {
        this.speed = this.speed * (0.99 + Math.random() / 100);
      } else {
        this.stop();
        return;
      }
    }
    if (this.rotating && !this.autoStop) {
      this.timer = (this.timer + this.constSpeed) % 1000;
    }

    this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);

//    this.ctx.fillStyle = "#2d2d2d";
this.ctx.fillStyle = "#FCFCFC";
    this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height);

    // 转盘初始化
    this.ctx.save();
    this.ctx.translate(this.canvas.width / 2, this.paddingTop + this.radius);
    this.ctx.rotate(-Math.PI / 2);
    this.ctx.strokeStyle = "#8E8E8E";
    this.ctx.lineWidth = this.lineWidth;
    this.ctx.lineCap = "round";

    // 转盘底座
    this.ctx.beginPath();
    this.ctx.fillStyle = "#BEBEBE";
    this.ctx.arc(0, 0, this.radius + 20, 0, Math.PI * 2, false);
    this.ctx.fill()

    // 转盘内容（旋转部分）
    this.ctx.save();
    this.ctx.rotate(Math.PI * 2 * (this.timer / 1000));
    this.ctx.strokeStyle = "#FFFCEC";
    this.list.forEach((item, index) => {
      // 色块部分
      const colorIndex = index % this.colorList.length 
      this.ctx.fillStyle = this.colorList[colorIndex];
      this.ctx.beginPath();
      this.ctx.moveTo(0, 0);
      this.ctx.lineTo(this.radius, 0);
      this.ctx.arc(0, 0, this.radius, 0, Math.PI * 2 * item.pct);
      this.ctx.rotate(Math.PI * 2 * item.pct);
      this.ctx.lineTo(0, 0);
      this.ctx.stroke();
      this.ctx.fill();
      // 文字部分
      this.ctx.save();
      this.ctx.fillStyle = "#F7F4E8";
      this.ctx.font = `normal bold ${this.fontSize}px sans-serif`;
      this.ctx.rotate(-(Math.PI * 2 * item.pct) / 2);
      item.name.split("").forEach((text, index) => {
        this.ctx.save();
        this.ctx.translate(
          this.radius - (index + 1) * (this.fontSize + 3),
          - this.fontSize / 2
        );
        this.ctx.rotate(Math.PI / 2);
        this.ctx.fillText(item.name[index], 0, 0);
        this.ctx.restore();
      });
      this.ctx.restore();
    });
    this.ctx.restore();

    // 指针
    this.ctx.save();
    this.ctx.strokeStyle = "#8E8E8E";
    this.ctx.fillStyle = "#8E8E8E";
    this.ctx.lineWidth = this.lineWidth;
    this.ctx.beginPath();
    this.ctx.rotate(-Math.PI / 7);
    this.ctx.moveTo(this.radius / 7, 0, 0);
    this.ctx.rotate(Math.PI / 7);
    this.ctx.lineTo(this.canvas.width / 10, 0);
    this.ctx.rotate(Math.PI / 7);
    this.ctx.lineTo(this.radius / 7, 0);
    this.ctx.stroke();
    this.ctx.fill();
    this.ctx.restore();

    this.ctx.beginPath();
    this.ctx.fillStyle = "#8E8E8E";
    this.ctx.arc(0, 0, this.radius / 6, 0, Math.PI * 2, false);
    this.ctx.fill();

    this.ctx.beginPath();
    this.ctx.lineWidth = this.lineWidth / 2;
    this.ctx.arc(0, 0, this.radius / 6, -Math.PI / 8, Math.PI / 8, true);
    this.ctx.stroke();
    
    this.ctx.restore();
    
    // 结果
    let percentage = this.timer / 1000;
    let res = "";
    []
      .concat(this.list)
      .reverse()
      .some((item, index, arr) => {
        percentage = percentage - item.pct;
        if (percentage < 0) {
          res = arr[index].name;
          return true;
        }
    })
    this.ctx.fillStyle = "#336666";
    this.ctx.font = `normal bold ${this.fontSize + 10}px sans-serif`;
    this.ctx.fillText(res, (this.canvas.width - this.ctx.measureText(res).width)/2 , this.paddingTop / 2)

    if (this.rotating) {
      this.raf = window.requestAnimationFrame(() => {
        this.draw();
      });
    }
  }

  rotate() {
    this.rotating = true;
    this.raf = window.requestAnimationFrame(() => {
      this.draw();
    });
  }

  stop() {
    this.rotating = false;
    console.log(this.rotating, "rotaring");
    console.log(this.raf, "raf");
    // window.cancelAnimationFrame(this.raf);
    this.speed = this.initSpeed;
    let percentage = this.timer / 1000;
    console.log(percentage, "percentage");
    let res = "";
    []
      .concat(this.list)
      .reverse()
      .some((item, index, arr) => {
        percentage = percentage - item.pct;
        console.log(percentage, "percentage--");
        if (percentage < 0) {
          console.log(index, "index");
          res = arr[index].name;
          return true;
        }
      });
    if(res==="三千折百"){
	alert("恭喜抽中"+res+": gym100");	
}else if(res==="滿千折20"){
	alert("恭喜抽中"+res+": gym20");
}else{
	alert(res);
}
    
  }
}

const turntable = new Turntable({
  canvas: canvas,
  ctx: ctx,
  list: list,
  colorList: colorList,
//radius: 200,
  radius: 120,
  lineWidth: 8,
  paddingTop: 120,
  fontSize: 16,
  initSpeed: 15,
  constSpeed: 60,
  autoStop: true
});

turntable.draw();
canvas.addEventListener("click", () => {
  console.log(turntable.rotating);
  if(turntable.rotating && !turntable.autoStop) {
    turntable.stop()
    return
  }
  if (!turntable.rotating) {
    turntable.rotate();
    return
  }
});
