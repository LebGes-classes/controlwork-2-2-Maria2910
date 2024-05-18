import java.util.Scanner;

public class ControlWork_2 {
    public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		List<Programm> list = new List<>();
		int n = scan.nextInt();
		for(int i = 0, i < n, i++) {
			addProgramm(list);
		}
		printSortedByTime(list);
		findCurrent(list, 10, 10);
		findByProgrammName(list, "programmName");
		findByChannelName(list, "channelName");
		findCurrentByProgrammName(list, "programmName", 10, 10);
		findByInterval(list, 10, 10, 20, 20);
		
		public void addProgramm(List<Programm> list) {
			String newChannelName = scan.nextString();
			String newProgrammName = scan.nextString();
			byte hours = scan.nextInt();
			byte minutes = scan.nextInt();
			Programm newProgramm = new Programm(newChannelName, newProgrammName, hours, minutes);
			list.add(newProgramm);
		}
		
		public void printProgramm(Programm p) {
			System.out.println("channel name: " + p.channelName);
			System.out.println("programm name: " + p.programmlName);
			System.out.println("time: " + p.BroadcastsTime.hours + "hours, " + p.BroadcastsTime.minutes + "minutes");
			System.out.println();
		}
		
		
		public void printSortedByTime(List<Programm> list) {
			Collections.sort(list, ProgrammComporator);
			for(int i = 0, i < list.length(), i++) {
				Programm p = list(i);
				printProgramm(p);
			}
		}
		
		public class ProgrammComporator implements Comporator {
			public int compare(Programm p1, Programm p2) {
				return p1.time.compareTo(p2.time);
			}
		}
		
		public void findCurrent(List<Programm> list, byte hours, byte minutes) {
			for(int i = 0, i < list.length(), i++) {
				Programm p = list(i);
			    int programmTime = p.time();
			    if(programmTime == hours*60 + minutes) {
				    printProgramm(p);
			    }
			}
		}
		
		public void findByProgrammName(List<Programm> list, String name) {
			for(int i = 0, i < list.length(), i++) {
				Programm p = list(i);
			    String programmName = p.programmName;
			    if(programmName == name) {
				    printProgramm(p);
			    }
			}
		}
		
		public void findByChannelName(List<Programm> list, String name) {
			for(int i = 0, i < list.length(), i++) {
				Programm p = list(i);
			    String channelName = p.channelName;
			    if(channelName == name) {
				    printProgramm(p);
			    }
			}
		}
		
		public void findCurrentByProgrammName(List<Programm> list, String name, byte hours, byte minutes) {
			for(int i = 0, i < list.length(), i++) {
				Programm p = list(i);
			    String programmName = p.programmName;
				int time = p.time();
			    if(programmName == name && time == (hours*60 + minutes)) {
				    printProgramm(p);
			    }
			}
		}
		
		public void findByInterval(List<Programm> list, byte hours1, byte minutes1, byte hours2, byte minutes2) {
			for(int i = 0, i < list.length(), i++) {
				Programm p = list(i);
				int time = p.time();
				int border1 = hours1*60 + minutes1;
				int border2 = hours2*60 + minutes2;
			    if(time >= border1 && time <= border2) {
				    printProgramm(p);
			    }
			}
		}
		
	}
}

class BroadcastsTime implements Comparable {
	private byte hours;
	private byte minutes;
	
	public BroadcastsTime(byte hours, byte minutes) {
		this.hours = hours;
		this.minutes = minutes;
	}
	
	public void setHours(byte hours) {
		if(hours >= 0 && hours <= 24) {
			this.hours = hours;
		}
	}
	
	public void setMinutes(byte minutes) {
		if(minutes >= 0 && minutes < 60) {
			this.minutes = minutes;
		}
	}
	
	public byte hours() {
		return hours;
	}
	
	public byte minutes() {
		return minutes;
	}
	
	public boolean after(BroadcastsTime t) {
		return (hours()*60 + minutes()) > (t.hours()*60 + t.minutes());
	}
	
	public boolean before(BroadcastsTime t) {
		return (hours()*60 + minutes()) < (t.hours()*60 + t.minutes());
	}
	
	public boolean between(BroadcastsTime t1, BroadcastsTime t2) {
		return after(t1) && before(t2);
	}
	
	public int CompareTo(BroadcastsTime t) {
		return (hours()*60 + minutes()) - (t.hours()*60 + t.minutes());
	}
}

class Programm {
	private String channelName;
	private String programmName;
	private BroadcastsTime time;
	
	public Programm(String channelName, String programmName, Broadcasts time) {
		this.channelName = channelName;
		this.programmName = programmName;
		this.time = time;
	}
	
	public Programm(String channelName, String programmName, byte hours, byte minutes) {
		this.channelName = channelName;
		this.programmName = programmName;
		BroadcastsTime newTime = new BroadcastsTime(hours, minutes);
	    time = newTime;
	}
	
	public int time() {
		return time.hours*60 + time.minutes;
	}
}
