/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PneumaticsSstm extends SubsystemBase {
  
  private final Compressor m_compressor = new Compressor(Constants.PCM_MODULE);

  public PneumaticsSstm() {

  }

  public double getCompressorCurrent() {
    return m_compressor.getCompressorCurrent();
  }

  public boolean getPressureSwitchValue() {
    return m_compressor.getPressureSwitchValue();
  }

  public boolean getEnabled() {
    return m_compressor.enabled();
  }

  public void startCompressor(){
    m_compressor.start();
  }

  public void stopCompressor(){
    m_compressor.stop();
  }
}
