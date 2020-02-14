/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.XboxController;

public class ballmechanism extends SubsystemBase {
  private final double INTAKE_SPEED = -0.25;
  private final double OUTTAKE_SPEED = .5;
  private final double TOPOUTTAKE_SPEED = 1;
  private final double TOPINTAKE_SPEED = -1;
    
  private final WPI_TalonSRX m_BottomIntakeMotor1 = new WPI_TalonSRX(7);
  private final WPI_TalonSRX m_BottomIntakeMotor2 = new WPI_TalonSRX(8);
  private final WPI_TalonSRX m_TopIntakeMotor = new WPI_TalonSRX(6);

  private final XboxController m_stick = new XboxController(0);
  //private RobotContainer m_robotContainer;
  /**
   * Creates a new ballmechanism.
   */
  public ballmechanism() {
    m_BottomIntakeMotor2.follow(m_BottomIntakeMotor1);
    //m_robotContainer = new RobotContainer();
  }

  @Override
  public void periodic() {
         
    if (m_stick.getXButton()) {
      m_BottomIntakeMotor1.set(OUTTAKE_SPEED);
      m_TopIntakeMotor.set(TOPOUTTAKE_SPEED);
       } 
       else if (m_stick.getYButton()){
        m_BottomIntakeMotor1.set(INTAKE_SPEED);
        m_TopIntakeMotor.set(TOPINTAKE_SPEED);
      // stop motor
    }else {
      m_BottomIntakeMotor1.set(0);
     // stop motor
   }
    // This method will be called once per scheduler run
  }
}
