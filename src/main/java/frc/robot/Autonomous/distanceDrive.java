import DriveSstm;

public class distanceDrive {
    
    private DriveSstm driveSystem;
    private double defaultDistance;
    private double brakingDistance;

    /**
     * @param defaultDistance the target length if no custom length is set
     * @param brakingDistance a length from the target defaultDistance he
                              robots starts to slow down
              (This variable is entirely my imagination)
    
     */
    public distanceDrive(double defaultDistance, double brakingDistance) {
        driveSystem = new DriveSstm();
        this.defaultDistance = defaultDistance;
        this.brakingDistance = brakingDistance;
    }

    /**
     * Drives the robot straight ahead for a certain defaultDistance      
     *
     * @param distance the forward length to be travelled
     */
    public void driveDistance(double distance) {  
        driveSystem.zeroLEncoder();
        driveSystem.zeroREncoder();

        while(true) {
            distanceLeft = distance - driveSystem.getAverageEncoderDistance();
            if(distanceLeft >= brakingDistance) {
                driveSystem.setMaxOutput(Constants.BOOST_SPEED);
                driveSyste.tankDrive(1.0, 1.0);
            } else if (distanceLeft < brakingDistance) {
                driveSystem.setMaxOutput(Constants.PRECISION_SPEED);
                driveSystem.tankDrive(1.0, 1.0);
            } else if (distanceLeft <= 0.0) {
                break;
            }
        }

        driveSystem.setMaxOutput(Constants.NORMAL_SPEED);
    }

    /**
     * Drives the robot straight ahead for a default distance 
     */
    public void driveDistance() {
        driveDistance(this.defaultDistance);
}