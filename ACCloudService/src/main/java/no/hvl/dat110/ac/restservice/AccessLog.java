package no.hvl.dat110.ac.restservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class AccessLog {
	
	// atomic integer used to obtain identifiers for each access entry
	private AtomicInteger cid;
	protected ConcurrentHashMap<Integer, AccessEntry> log;
	
	public AccessLog () {
		this.log = new ConcurrentHashMap<Integer,AccessEntry>();
		cid = new AtomicInteger(0);
	}

// TODO: add an access entry to the log for the provided message and return assigned id
	public int add(String message) {

		int id = cid.getAndAdd(1);
		log.put(id,new AccessEntry(id,message));
		return id;
	}

	// TODO: retrieve a specific access entry from the log
	public AccessEntry get(int id) throws IndexOutOfBoundsException{
		if(!log.containsKey(id)) {
			throw new IndexOutOfBoundsException();
		}
		return log.get(id);
		
	}
	
	// TODO: clear the access entry log
	public void clear() {
		log.clear();
	}
	
	// TODO: return JSON representation of the access log
	public String toJson () {
    	
		String json = "[";

		Gson gson = new Gson();
		List<AccessEntry> entries = new ArrayList<>(log.values());

		for (int i = 0; i < entries.size(); i++) {
			AccessEntry ae = entries.get(i);
			json += gson.toJson(ae);
			if(i != entries.size()-1){
				json += ",";
			}
		}

		json +="]";
    	return json;
    }
}
