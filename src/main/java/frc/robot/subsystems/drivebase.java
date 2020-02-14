/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.Encoder;

public class drivebase extends SubsystemBase {
  double desiredDistance = 120;
  NetworkTableEntry xEntry;
  NetworkTableEntry yEntry;
  private static final int kEncoderPortA = 0;
  private static final int kEncoderPortB = 1;
  private Encoder m_encoder;
  private static final int kEncoderPortC = 2;
  private static final int kEncoderPortD = 3;
  private Encoder m_encoder2;
  private Command m_autonomousCommand;
  // private final DifferentialDrive m_robotDrive = new DifferentialDrive(new WPI_VictorSPX(3), new WPI_VictorSPX(4));
    // drive motors
    private final WPI_VictorSPX m_leftMotor = new WPI_VictorSPX(5);
    private final WPI_VictorSPX m_rightMotor = new WPI_VictorSPX(3);
    private final WPI_VictorSPX m_leftfollow = new WPI_VictorSPX(2);
    private final WPI_VictorSPX m_rightfollow = new WPI_VictorSPX(4);
  
    private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
    private final XboxController m_stick = new XboxController(0);
/**
   * Change the I2C port below to match the connection of your color sensor
   */
    
    private final Timer m_timer = new Timer();
    private RobotContainer m_robotContainer;

  
  /**
   * Creates a new drivebase.
   */
  public drivebase() {
  //Get the default instance of NetworkTables that was created automatically
       //when your program starts
       NetworkTableInstance inst = NetworkTableInstance.getDefault();
        //Get the table within that instance that contains the data. There can
       //be as many tables as you like and exist to make it easier to organize
       //your data. In this case, it's a table called datatable.
       NetworkTable table = inst.getTable("datatable");

       //Get the entries within that table that correspond to the X and Y values
       //for some operation in your program.
       xEntry = table.getEntry("X");
       yEntry = table.getEntry("Y");
    m_leftfollow.follow(m_leftMotor);
    m_rightfollow.follow(m_rightMotor);
    
    //  m_BottomIntakeMotor2.follow(m_BottomIntakeMotor1);
      
    
    
      
    m_encoder = new Encoder(kEncoderPortA, kEncoderPortB);
    m_encoder2 = new Encoder(kEncoderPortC, kEncoderPortD);

    // Use SetDistancePerPulse to set the multiplier for GetDistance
    // This is set up assuming a 6 inch wheel with a 360 CPR encoder.

    m_encoder.setDistancePerPulse((Math.PI * 6) / 360.0);
    m_encoder2.setDistancePerPulse((Math.PI * 6) / 360.0);  
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

 

 
  }
  double x = 0;
  double y = 0;

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  }

  @Override
  public void periodic() {
    
    SmartDashboard.putNumber("Encoder", m_encoder.getDistance());
    SmartDashboard.putNumber("Encoder2", m_encoder2.getDistance());
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    // This method will be called once per scheduler run
  }
}
