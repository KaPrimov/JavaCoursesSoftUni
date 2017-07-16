package P02_FileStream;

public class StreamProgressInfo {
    private Streamable streamResult;

    // If we want to stream a music file, we can't
    public StreamProgressInfo(Streamable streamable) {
        this.streamResult = streamable;
    }

    public int calculateCurrentPercent() {
        return (this.streamResult.getBytesSent() * 100) / this.streamResult.getLength();
    }

    public static void main(String[] args) {
        Streamable streamable = new Music();
        StreamProgressInfo streamProgressInfo = new StreamProgressInfo(streamable);
        System.out.println(streamProgressInfo.calculateCurrentPercent());
    }
}
