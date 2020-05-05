package com.sherbenko;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Optional;

public class FindCountry {
    private final static String IP_BEL = "127.0.0.1";

    public static String findCountryFromDb() throws IOException, GeoIp2Exception {

        String ip = fetchClientIpAddr();

        if (ip.equals(IP_BEL)) {
            return "Belarus";
        }

        /*Path to GEO2 BD GeoLite2-Country.mmdb*/
        String dbLocation = "C:/Users/ilyxa/Downloads/GeoLite2-Country_20200428/GeoLite2-Country.mmdb";

        File database = new File(dbLocation);
        DatabaseReader dbReader = new DatabaseReader.Builder(database).build();

        InetAddress ipAddress = InetAddress.getByName(ip);
        CountryResponse response = dbReader.country(ipAddress);

        return response.getCountry().getName();
    }

    private static String fetchClientIpAddr() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
        String ip = Optional.ofNullable(request.getHeader("X-FORWARDED-FOR")).orElse(request.getRemoteAddr());
        if (ip.equals("0:0:0:0:0:0:0:1")) ip = "127.0.0.1";
        Assert.isTrue(ip.chars().filter($ -> $ == '.').count() == 3, "Illegal IP: " + ip);
        return ip;
    }
}
