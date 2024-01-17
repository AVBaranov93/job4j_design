package ru.job4j.serialization;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Shop {
    @XmlAttribute
    private boolean isOpen;
    @XmlAttribute
    private double square;
    @XmlAttribute
    private String shopName;
    private Product product;
    @XmlElementWrapper(name = "addresses")
    @XmlElement(name = "address")
    private String[] addresses;

    public Shop() {
    }

    public Shop(boolean isOpen, double square, String shopName, Product product, String[] addresses) {
        this.isOpen = isOpen;
        this.square = square;
        this.shopName = shopName;
        this.product = product;
        this.addresses = addresses;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public double getSquare() {
        return square;
    }

    public String getShopName() {
        return shopName;
    }

    public Product getProduct() {
        return product;
    }

    public String[] getAddresses() {
        return addresses;
    }

    @Override
    public String toString() {
        return "Shop{"
                + "isOpen=" + isOpen
                + ", square=" + square
                + ", shopName='" + shopName + '\''
                + ", product=" + product
                + ", addresses=" + Arrays.toString(addresses)
                + '}';
    }

    public static void main(String[] args) throws Exception {
        Shop shop = new Shop(true, 100, "shop", new Product("apple", 9.99),
                new String[] {"firstAddr", "secondAddr", "thirdAddr"});
        JAXBContext context = JAXBContext.newInstance(Shop.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(shop, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Shop deserialisedShop = (Shop) unmarshaller.unmarshal(reader);
            System.out.println(deserialisedShop);
        }

        JSONObject jsonObject = new JSONObject(shop);
        System.out.println(jsonObject);
        JSONArray jsonArray = new JSONArray(List.of(shop.getAddresses()));
        System.out.println(jsonArray);

    }
}
