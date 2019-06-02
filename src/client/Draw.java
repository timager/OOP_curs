package client;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import server.*;

class Draw {
    private Building building;
    private Canvas repairShopCanvas, carWashCanvas, vehicleInspectionCanvas;

    Draw(Canvas repairShopCanvas, Canvas carWashCanvas, Canvas vehicleInspectionCanvas) {
        this.repairShopCanvas = repairShopCanvas;
        this.carWashCanvas = carWashCanvas;
        this.vehicleInspectionCanvas = vehicleInspectionCanvas;
    }

    private void drawRepairShop() {
        RepairShop repairShop = building.getRepairShop();
        Car car = repairShop.getCar();
        if (car != null) {
            repairShopCanvas.getGraphicsContext2D().fillText(repairShop.getCar().toString(), 10, 10);
            drawCar(repairShopCanvas, car);

        }
    }

    private void drawCarWash() {
        CarWash carWash = building.getCarWash();
        Car car = carWash.getCar();
        if (car != null) {
            carWashCanvas.getGraphicsContext2D().fillText(carWash.getCar().toString(), 10, 10);
            drawCar(carWashCanvas, car);

        }
    }

    private void drawVehicleInspection() {
        VehicleInspection vehicleInspection = building.getVehicleInspection();
        Car car = vehicleInspection.getCar();
        if (car != null) {
            vehicleInspectionCanvas.getGraphicsContext2D().fillText(car.toString(), 10, 10);
            drawCar(vehicleInspectionCanvas, car);
        }
    }

    void drawBuilding(Building building) {
        this.building = building;
        clearCanvases();
        drawRepairShop();
        drawCarWash();
        drawVehicleInspection();
        drawMaster();
    }

    private void clearCanvases() {
        repairShopCanvas.getGraphicsContext2D().clearRect(0, 0, repairShopCanvas.getWidth(), repairShopCanvas.getHeight());
        carWashCanvas.getGraphicsContext2D().clearRect(0, 0, carWashCanvas.getWidth(), carWashCanvas.getHeight());
        vehicleInspectionCanvas.getGraphicsContext2D().clearRect(0, 0, vehicleInspectionCanvas.getWidth(), vehicleInspectionCanvas.getHeight());
    }

    private void drawCar(Canvas canvas, Car car) {
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setFill(Color.BLACK);
        int x = 30;
        int y= 30;
        context.fillRect(x-1, y-1, 32, 12);
        context.setFill(Color.rgb(car.r, car.g, car.b));
        context.fillRect(x, y, 30, 10);
        context.setFill(Color.BLACK);
        context.fillRect(x+5, y-5, 20, 5);
        context.fillOval(x, x+5, 10, 10);
        context.fillOval(x+20, y+5, 10, 10);
    }

    private void drawMaster() {
        Master master = building.getMaster();
        Canvas canvas;
        if (master.getFabric() instanceof RepairShop) {
            canvas = repairShopCanvas;
        } else if ((master.getFabric() instanceof CarWash)) {
            canvas = carWashCanvas;
        } else {
            canvas = vehicleInspectionCanvas;
        }
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setFill(Color.PINK);
        context.fillOval(38, 60, 25, 25);
        context.setFill(Color.BLACK);
        context.fillText(master.getTimeLeft()/(1000/Server.SLEEP)+"c.",46,75,10);
    }
}
