package pl.jaworskimateusz.machineapi.exception;

public class ErrorResponseTimeString extends CustomErrorResponse {

    private String timestamp;

    public ErrorResponseTimeString(String timestamp, String error, int status) {
        super(error, status);
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
