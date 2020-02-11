/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.XboxController;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class ExampleSubsystem extends SubsystemBase {
  private static final int kEncoderPortA = 0;
  private static final int kEncoderPortB = 1;
  private Encoder m_encoder;
  private static final int kEncoderPortC = 2;
  private static final int kEncoderPortD = 3;
  private Encoder m_encoder2;
  private final WPI_VictorSPX m_leftMotor = new WPI_VictorSPX(5);
  private final WPI_VictorSPX m_rightMotor = new WPI_VictorSPX(3);
  private final WPI_VictorSPX m_leftfollow = new WPI_VictorSPX(2);
  private final WPI_VictorSPX m_rightfollow = new WPI_VictorSPX(4);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  private final XboxController m_stick = new XboxController(0);
  /**
   * Creates a new ExampleSubsystem.
   */
  public ExampleSubsystem() {
    m_leftfollow.follow(m_leftMotor);
    m_rightfollow.follow(m_rightMotor);
    m_encoder = new Encoder(kEncoderPortA, kEncoderPortB);
    m_encoder2 = new Encoder(kEncoderPortC, kEncoderPortD);

    m_encoder.setDistancePerPulse((Math.PI * 6) / 360.0);
    m_encoder2.setDistancePerPulse((Math.PI * 6) / 360.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Encoder", m_encoder.getDistance());
    SmartDashboard.putNumber("Encoder2", m_encoder2.getDistance());
    m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());
  }
}
