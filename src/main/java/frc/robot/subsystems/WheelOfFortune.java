/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorSensorV3.RawColor;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.XboxController;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.subsystems.ExampleSubsystem;

public class WheelOfFortune extends SubsystemBase {   
  private final XboxController m_stick = new XboxController(0);
  private final WPI_VictorSPX m_leftMotor = new WPI_VictorSPX(5);
    private final WPI_VictorSPX m_rightMotor = new WPI_VictorSPX(3);
    private final WPI_VictorSPX m_leftfollow = new WPI_VictorSPX(2);
    private final WPI_VictorSPX m_rightfollow = new WPI_VictorSPX(4);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
    
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  /**
   * A Rev Color Sensor V3 object is constructed with an I2C port as a 
   * parameter. The device will be automatically initialized with default 
   * parameters.
   */
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  
 // public static class ColorSensorV3.RawColor
    private final ColorMatch m_colorMatcher = new ColorMatch();
  /**
   * Creates a new WheelOfFortune.
   */private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
 
  public WheelOfFortune() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);   
    m_leftfollow.follow(m_leftMotor);
    m_rightfollow.follow(m_rightMotor);
   /*m_encoder = new Encoder(kEncoderPortA, kEncoderPortB);
   m_encoder2 = new Encoder(kEncoderPortC, kEncoderPortD);*/

  }

  @Override
  public void periodic() {
    String colorString;
    Color detectedColor = m_colorSensor.getColor();
    RawColor detectedRawColor = m_colorSensor.getRawColor();
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    /**
     * The sensor returns a raw IR value of the infrared light detected.
     */
    double IR = m_colorSensor.getIR();
     /**
     * Open Smart Dashboard or Shuffleboard to see the color detected by the 
     * sensor.
     */
    //Color detectedrawcolor = GetRawColor(detectedColor);
    //SmartDashboard.putNumber("Leslie", detectedColor.getRawColor());
 
    boolean booleanRed = false;
    boolean booleanGreen = false;
    boolean booleanBlue = false;
    boolean booleanYellow = false;
 /**
     * In addition to RGB IR values, the color sensor can also return an 
     * infrared proximity value. The chip contains an IR led which will emit
     * IR pulses and measure the intensity of the return. When an object is 
     * close the value of the proximity will be large (max 2047 with default
     * settings) and will approach zero when the object is far away.
     * 
     * Proximity can be used to roughly approximate the distance of an object
     * or provide a threshold for when an object is close enough to provide
     * accurate color values.
     */
    int proximity = m_colorSensor.getProximity();

    SmartDashboard.putNumber("Proximity", proximity);
    String egg;
    egg = "";
    String gameData;
gameData = DriverStation.getInstance().getGameSpecificMessage();
if(gameData.length() > 0)
{
  switch (gameData.charAt(0))
  {
    case 'B' :
      //Blue case code
      SmartDashboard.putString("FMS Color", "Blue");
      egg="Blue";
      break;
    case 'G' :
      //Green case code
      SmartDashboard.putString("FMS Color", "Green");
      egg="Green";
      break;
    case 'R' :
      //Red case code 
      SmartDashboard.putString("FMS Color", "Red");
      egg="Red";
      break;
    case 'Y' :
      //Yellow case code
      SmartDashboard.putString("FMS Color", "Yellow");
      egg="Yellow";
      break;
    default :
      //This is corrupt data
      break;
  }
} else {
  //Code for no data received yet
}

if (m_stick.getYButton()){
  // drive forwards half speed
  if (match.color == kBlueTarget){
    // m_robotDrive.arcadeDrive(0.5, 0.0); 
     colorString = "Blue";
     booleanBlue =true;
     if (egg != "Blue"){
      m_robotDrive.arcadeDrive(0.5, 0.0); 
     }else{
      m_robotDrive.stopMotor();
     }
   }else if (match.color == kRedTarget){
     colorString = "Red";
    // 
     booleanRed = true;
     
   }else if (match.color == kGreenTarget){
     //m_robotDrive.arcadeDrive(0.5, 0.0); 
     colorString = "Green";
     booleanGreen = true;   

    }
   else if (match.color == kYellowTarget){
    // m_robotDrive.stopMotor();
     colorString = "Yellow";
     booleanYellow = true;   
     
    }
   else {
     colorString = "unknown";
   }

 SmartDashboard.putBoolean("isRed", booleanRed);
 SmartDashboard.putBoolean("isBlue", booleanBlue);
 SmartDashboard.putBoolean("isYellow", booleanYellow);
 SmartDashboard.putBoolean("isGreen", booleanGreen);
    // This method will be called once per scheduler run
  }
else{
  m_robotDrive.stopMotor();
}


}
}
