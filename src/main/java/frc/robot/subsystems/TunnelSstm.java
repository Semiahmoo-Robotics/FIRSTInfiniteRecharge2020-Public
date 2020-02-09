/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TunnelSstm extends SubsystemBase {
  
  //Intake
  private final Spark m_intakeSpark = new Spark(Constants.INTAKE_PORT);
  private final DoubleSolenoid m_intakeDoubleSolenoid = new DoubleSolenoid(Constants.INTAKE_PISTONS_FWD_PORT, Constants.INTAKE_PISTONS_REV_PORT);
  
  //Indexing Ultrasonic
  private final AnalogInput m_ultrasonic = new AnalogInput(0);
  
  //Shooter
  private final Spark m_lShooterSpark = new Spark(Constants.L_SHOOTER_PORT);
  private final Spark m_rShooterSpark = new Spark(Constants.R_SHOOTER_PORT);

  //Tunnel
  private final Spark m_lTunnelSpark = new Spark(Constants.L_TUNNEL_PORT);
  private final Spark m_rTunnelSpark = new Spark(Constants.R_TUNNEL_PORT);

  //Chamber
  private final Spark m_chamberSpark = new Spark(Constants.CHAMBER_PORT);


  public TunnelSstm() {
    m_lShooterSpark.setInverted(true);
  }

  public void startShooter() {
    m_lShooterSpark.set(1.0);
    m_rShooterSpark.set(1.0);
  }

  public void stopShooter() {
    m_lShooterSpark.stopMotor();
    m_rShooterSpark.stopMotor();
  }

  public void startTunnel() {
    m_lTunnelSpark.set(0.4);
    m_rTunnelSpark.set(0.4);
  }

  public void stopTunnel() {
    m_lTunnelSpark.stopMotor();
    m_rTunnelSpark.stopMotor();
  }

  public void extendIntake() {
    m_intakeDoubleSolenoid.set(Value.kForward);
  }

  public void retractIntake() {
    m_intakeDoubleSolenoid.set(Value.kReverse);
  }

  public void startIntake() {
    m_intakeSpark.set(0.4);
  }

  public void stopIntake() {
    m_intakeSpark.stopMotor();
  }

  public void reverseIntake() {
    m_intakeSpark.set(-0.2);
  }

  public double getVoltage() {
    return m_ultrasonic.getAverageVoltage();
  }
  
  public double getDistance() {
    return 5 * (m_ultrasonic.getAverageVoltage() * Constants.DEFAULT_SCALING_FACTOR);
  }

  public void startChamber() {
    m_chamberSpark.set(1.0);
  }

  public void stopChamber() {
    m_chamberSpark.stopMotor();
  }

  public void startShootSequence() {
    startChamber();
    startTunnel();
    stopIntake();
  }

  public void stopShootSequence() {
    stopChamber();
    stopTunnel();
  }

  public void startIntakeSequence() {
    startIntake();
    startTunnel();
    stopChamber();
  }

  public void stopIntakeSequence() {
    stopIntake();
    stopTunnel();
  }
}
