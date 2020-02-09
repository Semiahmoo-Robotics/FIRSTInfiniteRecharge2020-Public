/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SpinnerSstm;

public class RotationSpinCmd extends CommandBase {
  
  private final SpinnerSstm m_dialSpinner;
  private final ColorMatch m_colorMatcher;
  private Color kCalibrated;
  private int count = 0;
  private boolean finished = false;

  public RotationSpinCmd(SpinnerSstm dialSpinner) {
    this.m_dialSpinner = dialSpinner;
    m_colorMatcher = new ColorMatch();
    addRequirements(m_dialSpinner);
  }

  //Values require calibration, remember 90 deg offset for colors when calibrating.
  //TODO: Calibrate Color Values
  @Override
  public void initialize() {
    kCalibrated = m_dialSpinner.getColor();
    m_colorMatcher.addColorMatch(kCalibrated);
    m_dialSpinner.startSpin();
  }

  @Override
  public void execute() {
    ColorMatchResult match = m_colorMatcher.matchClosestColor(m_dialSpinner.getColor());
    if(match.color == kCalibrated) {
      count++;
    }
  }

  @Override
  public boolean isFinished() {
    if(count >= 3) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_dialSpinner.stopSpin();
  }
}