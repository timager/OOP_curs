public class Master {
    private Fabric fabric;

    public void work() throws InterruptedException {
        Thread.sleep(fabric.getWorkTime());
        if (fabric.nextStage()) {
            nextFabric();
        }
    }

    private void nextFabric() {
        this.fabric = this.fabric.getNextFabric();
    }
}
