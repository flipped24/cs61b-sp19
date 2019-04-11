public class Body{
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  public Body(double xP, double yP, double xV, double yV,double m,String img)
  {
    xxPos=xP;
    yyPos=yP;
    xxVel=xV;
    yyVel=yV;
    mass=m;
    imgFileName=img;
  }

  public Body(Body b)
  {
    xxPos=b.xxPos;
    yyPos=b.yyPos;
    xxVel=b.xxVel;
    yyVel=b.yyVel;
    mass=b.mass;
    imgFileName=b.imgFileName;
  }

  public double calcDistance(Body dog)
  {
  double res;
    res=(this.xxPos-dog.xxPos)*(this.xxPos-dog.xxPos)+(this.yyPos-dog.yyPos)*(this.yyPos-dog.yyPos);
    res=Math.pow(res,0.5);
    return res;
  }

  public double calcForceExertedBy(Body dog)
  {
    final double G=6.67e-11;
    return G*dog.mass*this.mass/(this.calcDistance(dog)*this.calcDistance(dog));
  }

  public double calcForceExertedByX(Body dog)
  {
    double F=this.calcForceExertedBy(dog);
    return F*(dog.xxPos-this.xxPos)/this.calcDistance(dog);
  }

  public double calcForceExertedByY(Body dog)
  {
    double F=this.calcForceExertedBy(dog);
    return F*(dog.yyPos-this.yyPos)/this.calcDistance(dog);
  }

  public double calcNetForceExertedByX(Body[] dog)
  {
    double Fx=0;
    for (int i=0;i<dog.length;i++)
    {
      if (!this.equals(dog[i]))
      {
        Fx +=this.calcForceExertedByX(dog[i]);
      }
    }
    return Fx;
  }

  public double calcNetForceExertedByY(Body[] dog)
  {
    double Fy=0;
    for (int i=0;i<dog.length;i++)
    {
      if (!this.equals(dog[i]))
      {
        Fy +=this.calcForceExertedByY(dog[i]);
      }
    }
    return Fy;
  }

  public void update(double dt,double fX,double fY)
  {
    double ax,ay;
    ax=fX/this.mass;
    ay=fY/this.mass;
    this.xxVel=this.xxVel+dt*ax;
    this.yyVel=this.yyVel+dt*ay;
    this.xxPos=this.xxPos+dt*this.xxVel;
    this.yyPos=this.yyPos+dt*this.yyVel;
  }

  public void draw(){
    String imgpath = "./images/"+this.imgFileName;
    StdDraw.picture(this.xxPos,this.yyPos,imgpath);
  }
}
/*xxPos,yyPos,xxVel,yyVel,mass,imgFileName*/
