package com.glacier.earthquake.monitor.server.configure.user;

import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.FilterPublicSentiment;
import com.glacier.earthquake.monitor.server.pojo.FilterWhiteList;
import com.glacier.earthquake.monitor.server.pojo.User;
import com.glacier.earthquake.monitor.server.util.Data2Object;
import com.glacier.earthquake.monitor.server.util.Object2Data;

import java.util.List;

/**
 * Created by glacier on 15-5-2.
 */
public class FilterRuleMonitor {

    private User user;

    public FilterRuleMonitor() {}

    public FilterRuleMonitor(User user) {
        this.user = user;
    }

    public List<FilterDisaster> getRuleDisasterList() {
        return Data2Object.filterRulesDisaster();
    }

    public boolean addRuleDisaster(FilterDisaster filterDisaster) {
        if ( user.getPrivilege() >= UserMonitor.USER_ADMINISTATOR ) {
            Object2Data.addFilterRulesDisaster(filterDisaster);
            return true;
        }
        return false;
    }

    public boolean setRuleDisaster(FilterDisaster filterDisaster) {
        if ( user.getPrivilege() >= UserMonitor.USER_ADMINISTATOR ) {
            Object2Data.setFilterRulesDisaster(filterDisaster);
            return true;
        }
        return false;
    }

    public boolean delRuleDisaster(Integer id) {
        if ( user.getPrivilege() >= UserMonitor.USER_ADMINISTATOR ) {
            Object2Data.delFilterRulesDisaster(id);
            return true;
        }
        return false;
    }

    public List<FilterPublicSentiment> getRulePubSentimentList() {
        return Data2Object.filterRulesPubSentiment();
    }

    public boolean addRulePubSentiment(FilterPublicSentiment filterPublicSentiment) {
        if ( user.getPrivilege() >= UserMonitor.USER_ADMINISTATOR ) {
            Object2Data.addFilterRulesPubSentiment(filterPublicSentiment);
            return true;
        }
        return false;
    }

    public boolean setRulePubSentiment(FilterPublicSentiment filterPublicSentiment) {
        if ( user.getPrivilege() >= UserMonitor.USER_ADMINISTATOR ) {
            Object2Data.setFilterRulesPubSentiment(filterPublicSentiment);
            return true;
        }
        return false;
    }

    public boolean delRulePubSentiment(Integer id) {
        if ( user.getPrivilege() >= UserMonitor.USER_ADMINISTATOR ) {
            Object2Data.delFilterRulesPubSentiment(id);
            return true;
        }
        return false;
    }

    public List<FilterWhiteList> getRuleWhiteLists() {
        return Data2Object.filterRulesWhiteList();
    }

    public boolean addRuleWhiteList(FilterWhiteList filterWhiteList) {
        if ( user.getPrivilege() >= UserMonitor.USER_ADMINISTATOR ) {
            Object2Data.addFilterRulesWhite(filterWhiteList);
            return true;
        }
        return false;
    }

    public boolean setRuleWhiteList(FilterWhiteList filterWhiteList) {
        if ( user.getPrivilege() >= UserMonitor.USER_ADMINISTATOR ) {
            Object2Data.setFilterWhiteList(filterWhiteList);
            return true;
        }
        return false;
    }

    public boolean delRuleWhiteList(Integer id) {
        if ( user.getPrivilege() >= UserMonitor.USER_ADMINISTATOR ) {
            Object2Data.delFilterWhiteList(id);
            return true;
        }
        return false;
    }

    public FilterDisaster getFilterDisasterByID(Integer id) {
        return Data2Object.getFilterDisasterByID(id);
    }

    public FilterPublicSentiment getFilterPubSentimentByID(Integer id) {
        return Data2Object.getFilterPubSentimentByID(id);
    }

    public FilterWhiteList getFilterWhiteListByID(Integer id) {
        return Data2Object.getFilterWhiteListByID(id);
    }

    public List<FilterPublicSentiment> getFilterPubSentimentByName(String name) {
        return Data2Object.getFilterPubSentimentByName(name);
    }

    public boolean truncateDisaster() {
        if ( user.getPrivilege() >= UserMonitor.USER_ADMINISTATOR ) {
            Data2Object.truncateDisaster();
            return true;
        }
        return false;
    }

    public boolean truncateWhiteList() {
        if ( user.getPrivilege() >= UserMonitor.USER_ADMINISTATOR ) {
            Data2Object.truncateWhiteList();
            return true;
        }
        return false;
    }

    public boolean truncatePubSentiment() {
        if ( user.getPrivilege() >= UserMonitor.USER_ADMINISTATOR ) {
            Data2Object.truncatePubSentiment();
            return true;
        }
        return false;
    }

}
