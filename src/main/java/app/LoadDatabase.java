package app;

import app.properties.RentApartment;
import app.repositories.RentApartmentRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(RentApartmentRepository repository) {

        return args -> {/*
            String lastURL = "";
            String newURL = "https://www.kv.ee/?act=search.simple&deal_type=2&county=12&search_type=new&parish=1063";
            int j = 0;
            //!lastURL.equals(newURL)
            while (j == 0) {
                Document doc = Jsoup.connect(newURL).userAgent("Chrome").get();

                Elements ads = doc.select(".swiper-gallery-view");
                for (Element ad : ads) {
                    String apartmentURL = ad.attr("data-object-url");
                    RentApartment rentApartment = new RentApartment();
                    Document adPage;

                    try {
                        adPage = Jsoup.connect(apartmentURL).userAgent("Chrome").get();
                    } catch (IOException e) {
                        log.error(e.getMessage());
                        continue;
                    }
                    Elements propertyMetaTags = adPage.getElementsByTag("meta").attr("property", "og:title");
                    for (Element propertyMetaTag : propertyMetaTags) {
                        String content = propertyMetaTag.attr("content");
                        if (content.startsWith("Anda üürile") && content.endsWith("Tartumaa")) {
                            rentApartment.setAddress(content.split(" - ")[1].split(", Tartu")[0]);
                            break;
                        }
                    }
                    rentApartment.setURL(apartmentURL);
                    rentApartment.setPrice(Float.parseFloat(adPage.select(".object-price").get(0).text().split(" €")[0].replace(" ", "")));
                    //rentApartment.setDescription(adPage.select(".object-article-body").get(0).text().split(" Võta")[0]);
                    Element apartmentTable = adPage.select(".table-lined").last();
                    assert apartmentTable != null;
                    Elements rows = apartmentTable.select("tr");
                    Elements cols = rows.select("th");
                    Elements values = rows.select("td");
                    for (int i = 1; i < cols.size(); i++) {
                        String value = values.get(i - 1).text();
                        switch (cols.get(i).text()) {
                            case "Tube" -> rentApartment.setRooms(Integer.parseInt(value));
                            case "Magamistube" -> rentApartment.setBedrooms(Integer.parseInt(value));
                            case "Üldpind" -> rentApartment.setSize(Float.parseFloat(value.split(" ")[0]));
                            case "Korrus/Korruseid" -> rentApartment.setFloor(Integer.parseInt(value.split("/")[0]));
                            case "Ehitusaasta" -> rentApartment.setRenovationYear(Integer.parseInt(value));
                            case "Seisukord" -> rentApartment.setPropertyCondition(value);
                            case "Energiamärgis" -> rentApartment.setEnergyLabel(value);
                        }
                    }
                    repository.save(rentApartment);
                }
                lastURL = newURL;
                Element nextPage = doc.select("[title~=Järgmine]").first();
                if (nextPage != null) newURL = "https://www.kv.ee/" + nextPage.attr("href");
                else break;
                log.info(newURL);
                j++;*/
            repository.save(new RentApartment());
        };
    }
}

