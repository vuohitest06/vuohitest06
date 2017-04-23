package com.service;

import com.dao.GameDao;
import com.dao.UserDao;
import com.domain.Game;
import com.domain.User;
import com.domain.Wish;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GoatProphet on 09/04/2017.
 */
@Stateless
public class SteamConnector implements Serializable {

    public List<Game> getWishListFromSteam(User user) throws IOException {
        Document doc = Jsoup.connect("http://steamcommunity.com/id/" + user.getSteamId() + "/wishlist/").get();
        Elements gameTitleElements = doc.select("div#wishlist_items");

        List<Game> resultList = new ArrayList<>();
        for(Element element : gameTitleElements) {
            String gameTitle = element.select("h4.ellipsis").html();
            String imageId = StringUtils.substringAfterLast(element.select("img").attr("src"), "/");
            String gameSteamId = StringUtils.substringAfterLast(element.select("div.storepage_btn_ctn a").attr("href"), "/");

            Game newGame = new Game(gameTitle, 0.0f, "", gameSteamId, imageId);
            resultList.add(newGame);
        }

        return resultList;
    }
}
