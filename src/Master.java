class Master {
    void setFabric(Fabric fabric) {
        this.fabric = fabric;
    }

    public Fabric getFabric() {
        return fabric;
    }

    private Fabric fabric;

    public long getTimeLeft() {
        return timeLeft;
    }

    private long timeLeft=0;

    void work() {
        if(timeLeft<=0||this.fabric.getCar()==null){
            nextFabric();
        }
        else{
            timeLeft--;
        }
    }

    private void nextFabric() {
        this.fabric.nextStage();
        this.fabric = this.fabric.getNextFabric();
        timeLeft = this.fabric.getWorkTime();
    }

    boolean isWork(){
        return this.fabric.getCar()!=null;
    }
}
