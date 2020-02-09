/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SpinnerSstm;

/* Color Conversion Table - What robot sensor sees and what field sensor sees is different.
  +------------+-------------+
  | FMS Target | Sensor Goal |
  +------------+-------------+
  |     'B'    |     'R'     |
  +------------+-------------+
  |     'G'    |     'Y'     |
  +------------+-------------+
  |     'R'    |     'B'     |
  +------------+-------------+
  |     'Y'    |     'G'     |
  +------------+-------------+
*/

public class PositionSpinCmd extends CommandBase {
  
  private final SpinnerSstm m_dialSpinner;
  private final ColorMatch m_colorMatcher;
  private Color kTarget;

  public PositionSpinCmd(SpinnerSstm dialSpinner) {
    this.m_dialSpinner = dialSpinner;
    m_colorMatcher = new ColorMatch();
    addRequirements(m_dialSpinner);
  }

  //Values require calibration, remember 90 deg offset for colors when calibrating.
  //TODO: Calibrate Color Values
  @Override
  public void initialize() {
    String gameData = DriverStation.getInstance().getGameSpecificMessage();
    if(gameData.length() > 0) this.cancel(); //Stage 3 not reached, Position Spin not needed.
    switch(gameData.charAt(0)) {
      case 'B':
        kTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
        break;
      case 'G':
        kTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
        break;
      case 'R':
        kTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
        break;
      case 'Y':
        kTarget = ColorMatch.makeColor(0.361, 0.524, 0.113); 
        break;
      default:
        this.cancel(); //Data is corrupt
    }

    m_colorMatcher.addColorMatch(kTarget);
    m_dialSpinner.startSpin();
  }

  @Override
  public void execute() {
  }

  @Override
  public boolean isFinished() {
    ColorMatchResult match = m_colorMatcher.matchClosestColor(m_dialSpinner.getColor());
    if(match.color == kTarget) {
      m_dialSpinner.stopSpin();
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void end(boolean interrupted){
    m_dialSpinner.stopSpin();
  }

}
