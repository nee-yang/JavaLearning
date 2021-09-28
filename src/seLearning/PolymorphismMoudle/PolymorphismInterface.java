package seLearning.PolymorphismMoudle;

// 利用接口实现多态

interface Car {
    String getName();
    int getPrice();
}

// 宝马
class BMW implements Car {

    @Override
    public String getName() {
        return "baoma";
    }

    @Override
    public int getPrice() {
        return 300000;
    }
}

// 奇瑞qq
class CherryQQ implements Car {
    @Override
    public String getName() {
        return "qq";
    }

    @Override
    public int getPrice() {
        return 40000;
    }
}

// 汽车出售店
class CarShop {
    private int sellMoney;

    public void sellCar(Car car) {
        System.out.println("type: " + car.getName() + "price: " + car.getPrice());
        sellMoney += car.getPrice();
    }

    public int getSellMoney() {
        return sellMoney;
    }
}

public class PolymorphismInterface {
    public static void main(String[] args) {

        CarShop carShop = new CarShop();
        Car BMW = new BMW();
        Car cherry = new CherryQQ();
        carShop.sellCar(BMW);
        carShop.sellCar(cherry);
        System.out.println("all sell money: " + carShop.getSellMoney());
    }
}
