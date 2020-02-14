/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorSensorV3.RawColor;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
public class colorsensor extends SubsystemBase {
   private final I2C.Port i2cPort = I2C.Port.kOnboard;
   private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  
 // public static class ColorSensorV3.RawColor
    private final ColorMatch m_colorMatcher = new ColorMatch();
    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);   
    private final XboxController m_stick = new XboxController(0);

  /**
   * Creates a new colorsensor.
   */
  public colorsensor() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);    

  }

  @Override
  public void periodic() {String colorString;
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
    // This method will be called once per scheduler run
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
}if (m_stick.getXButtonPressed()) {
  // drive forwards half speed
  if (match.color == kBlueTarget){
    // m_robotDrive.arcadeDrive(0.5, 0.0); 
     colorString = "Blue";
     booleanBlue =true;
     if (egg != "Blue"){
     // m_robotDrive.arcadeDrive(0.5, 0.0); 
     }else {
     // m_robotDrive.stopMotor();
     }
   }else if (match.color == kRedTarget){
     colorString = "Red";
    // m_robotDrive.stopMotor();
     booleanRed = true;
     if (egg != "Red"){
    //  m_robotDrive.arcadeDrive(0.5, 0.0); 
     }else {
     // m_robotDrive.stopMotor();
     }
   }else if (match.color == kGreenTarget){
     //m_robotDrive.arcadeDrive(0.5, 0.0); 
     colorString = "Green";
     booleanGreen = true;   
     if (egg != "Green"){
      //m_robotDrive.arcadeDrive(0.5, 0.0); 
     }else {
     // m_robotDrive.stopMotor();
     }
    }
   else if (match.color == kYellowTarget){
    // m_robotDrive.stopMotor();
     colorString = "Yellow";
     booleanYellow = true;   
     if (egg != "Yellow"){
     // m_robotDrive.arcadeDrive(0.5, 0.0); 
     }else {
      //m_robotDrive.stopMotor();
     }
    }
   else {
     colorString = "unknown";
   }
} else {
  //m_robotDrive.stopMotor(); // stop robot
}
 SmartDashboard.putBoolean("isRed", booleanRed);
 SmartDashboard.putBoolean("isBlue", booleanBlue);
 SmartDashboard.putBoolean("isYellow", booleanYellow);
 SmartDashboard.putBoolean("isGreen", booleanGreen);
  }
  }

