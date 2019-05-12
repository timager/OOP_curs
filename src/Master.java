class Master {
    void setFabric(Fabric fabric) {
        this.fabric = fabric;
    }

    private Fabric fabric;

    void work() throws InterruptedException {
        Thread.sleep(fabric.getWorkTime());
        if (fabric.nextStage()) {
            nextFabric();
        }
    }

    private void nextFabric() {
        this.fabric = this.fabric.getNextFabric();
    }
}
