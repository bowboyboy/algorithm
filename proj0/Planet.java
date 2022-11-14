public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img)
    {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass  = m;
        this.imgFileName = img;
    }
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass  = p.mass;
        this.imgFileName = p.imgFileName;
    }
    public double calcDistance( Planet p){
        double distance = 0;
        double rX = p.xxPos - this.xxPos;
        double rY = p.yyPos - this.yyPos;
        distance = Math.pow(Math.pow(rY,2)+Math.pow(rX,2) , 0.5);
        return distance;

    }
    public double calcForceExertedBy(Planet p){
        double G = 6.67e-11;
        double F = (G * this.mass * p.mass) / Math.pow(calcDistance(p),2);
        return  F;

    }
    public double calcForceExertedByX(Planet p){
        double rX = p.xxPos - this.xxPos;
        double rY = p.yyPos - this.yyPos;
        double distance = Math.pow(Math.pow(rY,2)+Math.pow(rX,2) , 0.5);
        double Fx = this.calcForceExertedBy(p) * rX / distance;
        return Fx;
    }
    public double calcForceExertedByY(Planet p){
        double rX = p.xxPos - this.xxPos;
        double rY = p.yyPos - this.yyPos;
        double distance = Math.pow(Math.pow(rY,2)+Math.pow(rX,2) , 0.5);
        double Fy = this.calcForceExertedBy(p) * rY / distance;
        return Fy;
    }
    public double calcNetForceExertedByX(Planet [] pa){
        double FnetX = 0 ;
        for(int i = 0; i < pa.length; i += 1)
        {
            if(pa[i] != this)
                FnetX =  FnetX + this.calcForceExertedByX(pa[i]);
        }
        return FnetX;
    }
    public double calcNetForceExertedByY(Planet [] pa){
        double FnetY = 0 ;
        for(int i = 0; i < pa.length; i += 1)
        {
            if(pa[i] != this)
                FnetY =  FnetY + this.calcForceExertedByY(pa[i]);
        }
        return FnetY;
    }
    private double acceleration(double f){
            return  f/this.mass;
    }
    private void  updateV(double dt,double fX, double fY){
        this.xxVel = this.xxVel + acceleration(fX) * dt ;
        this.yyVel = this.yyVel + acceleration(fY) * dt;

    }
    public void update (double dt, double fX, double fY){
        updateV(dt,fX,fY);
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;

    }
    public void draw(){
      String imageToDraw = "images/"+this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos,imageToDraw);
    }
}