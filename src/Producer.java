import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Scanner;

public class Producer {
    public static void main(String[] args) {

        KafkaProducer producer;
        Properties props=new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG,"producer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        producer=new KafkaProducer(props);

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the temperature value:");
        int Temp_value=input.nextInt();
        System.out.println("Enter the humidity value:");
        int Humidity_value=input.nextInt();

        String sendValue="{'temp':"+Temp_value+", 'humid':"+Humidity_value+"}"; //sending data as json object
        System.out.println(sendValue);

        producer.send(new ProducerRecord<>("TempMonitoringSystem", sendValue));
        producer.close();


    }
}

