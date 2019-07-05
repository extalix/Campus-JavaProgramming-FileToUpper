import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.io.File;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.StandardOpenOption;


class reader {
	private List<String>  readedFile;
	private Stream<String> source;
	
	public reader(String fileName) {
		try {
			source = Files.lines(new File(fileName).toPath());
			readedFile = source.collect(Collectors.toList());
		}
		catch (IOException e) {
			System.err.println("Kocheng nya gamau keluar");
		}
		catch (SecurityException e) {
			System.err.println("Satpamnya galak");
		}
	}
	
	public List<String> getReadedFile() {
		return this.readedFile;
	}
}

class writer {
	private List<String> bufferedFile;
	private String dFileName;
	
	public writer(String FileName, List<String> buffFile) {
		this.dFileName = FileName;
		this.bufferedFile = buffFile;
	}
	
	public void produceUpper() {
		try {
			List<String> temp = bufferedFile;
			// for (String line: temp) {
				// line = line.toUpperCase();
			// }
			temp.replaceAll(String::toUpperCase);
			Files.write(new File(this.dFileName).toPath(), temp, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
			return;
		}
		catch (IOException e) {
			System.err.println("KOCHENG GAMAU KELUAR");
		}
		catch (SecurityException e) {
			System.err.println("KOCENGNYA SEWA BODYGUARD");
		}
	}
}

public class fileProducer {
	public static void main(String []args) {
		reader source = new reader(".\\sorce.txt");
		writer destination = new writer(".\\UpperCase.txt", source.getReadedFile());
		destination.produceUpper();
	}
	
}