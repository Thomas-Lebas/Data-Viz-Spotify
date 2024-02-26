// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package dataviz.jeuxvideo_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: JeuxVideo Purpose: JeuxVideo<br>
 * Description: JeuxVideo <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class JeuxVideo implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "JeuxVideo";
	private final String projectName = "DATAVIZ";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					JeuxVideo.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(JeuxVideo.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tReplicate_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputJSON_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_DATAVIZ_JeuxVideo = new byte[0];
		static byte[] commonByteArray_DATAVIZ_JeuxVideo = new byte[0];

		public String Artist;

		public String getArtist() {
			return this.Artist;
		}

		public String Song;

		public String getSong() {
			return this.Song;
		}

		public String Duration_ms;

		public String getDuration_ms() {
			return this.Duration_ms;
		}

		public Boolean Explicit;

		public Boolean getExplicit() {
			return this.Explicit;
		}

		public String Year;

		public String getYear() {
			return this.Year;
		}

		public Integer Popularity;

		public Integer getPopularity() {
			return this.Popularity;
		}

		public String Danceability;

		public String getDanceability() {
			return this.Danceability;
		}

		public String Energy;

		public String getEnergy() {
			return this.Energy;
		}

		public String Key;

		public String getKey() {
			return this.Key;
		}

		public String Loudness;

		public String getLoudness() {
			return this.Loudness;
		}

		public String Mode;

		public String getMode() {
			return this.Mode;
		}

		public String Speechiness;

		public String getSpeechiness() {
			return this.Speechiness;
		}

		public String Acousticness;

		public String getAcousticness() {
			return this.Acousticness;
		}

		public String Instrumentalness;

		public String getInstrumentalness() {
			return this.Instrumentalness;
		}

		public String Liveness;

		public String getLiveness() {
			return this.Liveness;
		}

		public String Valence;

		public String getValence() {
			return this.Valence;
		}

		public String Tempo;

		public String getTempo() {
			return this.Tempo;
		}

		public String Genre;

		public String getGenre() {
			return this.Genre;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAVIZ_JeuxVideo.length) {
					if (length < 1024 && commonByteArray_DATAVIZ_JeuxVideo.length == 0) {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[1024];
					} else {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAVIZ_JeuxVideo, 0, length);
				strReturn = new String(commonByteArray_DATAVIZ_JeuxVideo, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAVIZ_JeuxVideo.length) {
					if (length < 1024 && commonByteArray_DATAVIZ_JeuxVideo.length == 0) {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[1024];
					} else {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAVIZ_JeuxVideo, 0, length);
				strReturn = new String(commonByteArray_DATAVIZ_JeuxVideo, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAVIZ_JeuxVideo) {

				try {

					int length = 0;

					this.Artist = readString(dis);

					this.Song = readString(dis);

					this.Duration_ms = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Explicit = null;
					} else {
						this.Explicit = dis.readBoolean();
					}

					this.Year = readString(dis);

					this.Popularity = readInteger(dis);

					this.Danceability = readString(dis);

					this.Energy = readString(dis);

					this.Key = readString(dis);

					this.Loudness = readString(dis);

					this.Mode = readString(dis);

					this.Speechiness = readString(dis);

					this.Acousticness = readString(dis);

					this.Instrumentalness = readString(dis);

					this.Liveness = readString(dis);

					this.Valence = readString(dis);

					this.Tempo = readString(dis);

					this.Genre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAVIZ_JeuxVideo) {

				try {

					int length = 0;

					this.Artist = readString(dis);

					this.Song = readString(dis);

					this.Duration_ms = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Explicit = null;
					} else {
						this.Explicit = dis.readBoolean();
					}

					this.Year = readString(dis);

					this.Popularity = readInteger(dis);

					this.Danceability = readString(dis);

					this.Energy = readString(dis);

					this.Key = readString(dis);

					this.Loudness = readString(dis);

					this.Mode = readString(dis);

					this.Speechiness = readString(dis);

					this.Acousticness = readString(dis);

					this.Instrumentalness = readString(dis);

					this.Liveness = readString(dis);

					this.Valence = readString(dis);

					this.Tempo = readString(dis);

					this.Genre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Artist, dos);

				// String

				writeString(this.Song, dos);

				// String

				writeString(this.Duration_ms, dos);

				// Boolean

				if (this.Explicit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.Explicit);
				}

				// String

				writeString(this.Year, dos);

				// Integer

				writeInteger(this.Popularity, dos);

				// String

				writeString(this.Danceability, dos);

				// String

				writeString(this.Energy, dos);

				// String

				writeString(this.Key, dos);

				// String

				writeString(this.Loudness, dos);

				// String

				writeString(this.Mode, dos);

				// String

				writeString(this.Speechiness, dos);

				// String

				writeString(this.Acousticness, dos);

				// String

				writeString(this.Instrumentalness, dos);

				// String

				writeString(this.Liveness, dos);

				// String

				writeString(this.Valence, dos);

				// String

				writeString(this.Tempo, dos);

				// String

				writeString(this.Genre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Artist, dos);

				// String

				writeString(this.Song, dos);

				// String

				writeString(this.Duration_ms, dos);

				// Boolean

				if (this.Explicit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.Explicit);
				}

				// String

				writeString(this.Year, dos);

				// Integer

				writeInteger(this.Popularity, dos);

				// String

				writeString(this.Danceability, dos);

				// String

				writeString(this.Energy, dos);

				// String

				writeString(this.Key, dos);

				// String

				writeString(this.Loudness, dos);

				// String

				writeString(this.Mode, dos);

				// String

				writeString(this.Speechiness, dos);

				// String

				writeString(this.Acousticness, dos);

				// String

				writeString(this.Instrumentalness, dos);

				// String

				writeString(this.Liveness, dos);

				// String

				writeString(this.Valence, dos);

				// String

				writeString(this.Tempo, dos);

				// String

				writeString(this.Genre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Artist=" + Artist);
			sb.append(",Song=" + Song);
			sb.append(",Duration_ms=" + Duration_ms);
			sb.append(",Explicit=" + String.valueOf(Explicit));
			sb.append(",Year=" + Year);
			sb.append(",Popularity=" + String.valueOf(Popularity));
			sb.append(",Danceability=" + Danceability);
			sb.append(",Energy=" + Energy);
			sb.append(",Key=" + Key);
			sb.append(",Loudness=" + Loudness);
			sb.append(",Mode=" + Mode);
			sb.append(",Speechiness=" + Speechiness);
			sb.append(",Acousticness=" + Acousticness);
			sb.append(",Instrumentalness=" + Instrumentalness);
			sb.append(",Liveness=" + Liveness);
			sb.append(",Valence=" + Valence);
			sb.append(",Tempo=" + Tempo);
			sb.append(",Genre=" + Genre);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_DATAVIZ_JeuxVideo = new byte[0];
		static byte[] commonByteArray_DATAVIZ_JeuxVideo = new byte[0];

		public String Artist;

		public String getArtist() {
			return this.Artist;
		}

		public String Song;

		public String getSong() {
			return this.Song;
		}

		public String Duration_ms;

		public String getDuration_ms() {
			return this.Duration_ms;
		}

		public Boolean Explicit;

		public Boolean getExplicit() {
			return this.Explicit;
		}

		public String Year;

		public String getYear() {
			return this.Year;
		}

		public Integer Popularity;

		public Integer getPopularity() {
			return this.Popularity;
		}

		public String Danceability;

		public String getDanceability() {
			return this.Danceability;
		}

		public String Energy;

		public String getEnergy() {
			return this.Energy;
		}

		public String Key;

		public String getKey() {
			return this.Key;
		}

		public String Loudness;

		public String getLoudness() {
			return this.Loudness;
		}

		public String Mode;

		public String getMode() {
			return this.Mode;
		}

		public String Speechiness;

		public String getSpeechiness() {
			return this.Speechiness;
		}

		public String Acousticness;

		public String getAcousticness() {
			return this.Acousticness;
		}

		public String Instrumentalness;

		public String getInstrumentalness() {
			return this.Instrumentalness;
		}

		public String Liveness;

		public String getLiveness() {
			return this.Liveness;
		}

		public String Valence;

		public String getValence() {
			return this.Valence;
		}

		public String Tempo;

		public String getTempo() {
			return this.Tempo;
		}

		public String Genre;

		public String getGenre() {
			return this.Genre;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAVIZ_JeuxVideo.length) {
					if (length < 1024 && commonByteArray_DATAVIZ_JeuxVideo.length == 0) {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[1024];
					} else {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAVIZ_JeuxVideo, 0, length);
				strReturn = new String(commonByteArray_DATAVIZ_JeuxVideo, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAVIZ_JeuxVideo.length) {
					if (length < 1024 && commonByteArray_DATAVIZ_JeuxVideo.length == 0) {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[1024];
					} else {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAVIZ_JeuxVideo, 0, length);
				strReturn = new String(commonByteArray_DATAVIZ_JeuxVideo, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAVIZ_JeuxVideo) {

				try {

					int length = 0;

					this.Artist = readString(dis);

					this.Song = readString(dis);

					this.Duration_ms = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Explicit = null;
					} else {
						this.Explicit = dis.readBoolean();
					}

					this.Year = readString(dis);

					this.Popularity = readInteger(dis);

					this.Danceability = readString(dis);

					this.Energy = readString(dis);

					this.Key = readString(dis);

					this.Loudness = readString(dis);

					this.Mode = readString(dis);

					this.Speechiness = readString(dis);

					this.Acousticness = readString(dis);

					this.Instrumentalness = readString(dis);

					this.Liveness = readString(dis);

					this.Valence = readString(dis);

					this.Tempo = readString(dis);

					this.Genre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAVIZ_JeuxVideo) {

				try {

					int length = 0;

					this.Artist = readString(dis);

					this.Song = readString(dis);

					this.Duration_ms = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Explicit = null;
					} else {
						this.Explicit = dis.readBoolean();
					}

					this.Year = readString(dis);

					this.Popularity = readInteger(dis);

					this.Danceability = readString(dis);

					this.Energy = readString(dis);

					this.Key = readString(dis);

					this.Loudness = readString(dis);

					this.Mode = readString(dis);

					this.Speechiness = readString(dis);

					this.Acousticness = readString(dis);

					this.Instrumentalness = readString(dis);

					this.Liveness = readString(dis);

					this.Valence = readString(dis);

					this.Tempo = readString(dis);

					this.Genre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Artist, dos);

				// String

				writeString(this.Song, dos);

				// String

				writeString(this.Duration_ms, dos);

				// Boolean

				if (this.Explicit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.Explicit);
				}

				// String

				writeString(this.Year, dos);

				// Integer

				writeInteger(this.Popularity, dos);

				// String

				writeString(this.Danceability, dos);

				// String

				writeString(this.Energy, dos);

				// String

				writeString(this.Key, dos);

				// String

				writeString(this.Loudness, dos);

				// String

				writeString(this.Mode, dos);

				// String

				writeString(this.Speechiness, dos);

				// String

				writeString(this.Acousticness, dos);

				// String

				writeString(this.Instrumentalness, dos);

				// String

				writeString(this.Liveness, dos);

				// String

				writeString(this.Valence, dos);

				// String

				writeString(this.Tempo, dos);

				// String

				writeString(this.Genre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Artist, dos);

				// String

				writeString(this.Song, dos);

				// String

				writeString(this.Duration_ms, dos);

				// Boolean

				if (this.Explicit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.Explicit);
				}

				// String

				writeString(this.Year, dos);

				// Integer

				writeInteger(this.Popularity, dos);

				// String

				writeString(this.Danceability, dos);

				// String

				writeString(this.Energy, dos);

				// String

				writeString(this.Key, dos);

				// String

				writeString(this.Loudness, dos);

				// String

				writeString(this.Mode, dos);

				// String

				writeString(this.Speechiness, dos);

				// String

				writeString(this.Acousticness, dos);

				// String

				writeString(this.Instrumentalness, dos);

				// String

				writeString(this.Liveness, dos);

				// String

				writeString(this.Valence, dos);

				// String

				writeString(this.Tempo, dos);

				// String

				writeString(this.Genre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Artist=" + Artist);
			sb.append(",Song=" + Song);
			sb.append(",Duration_ms=" + Duration_ms);
			sb.append(",Explicit=" + String.valueOf(Explicit));
			sb.append(",Year=" + Year);
			sb.append(",Popularity=" + String.valueOf(Popularity));
			sb.append(",Danceability=" + Danceability);
			sb.append(",Energy=" + Energy);
			sb.append(",Key=" + Key);
			sb.append(",Loudness=" + Loudness);
			sb.append(",Mode=" + Mode);
			sb.append(",Speechiness=" + Speechiness);
			sb.append(",Acousticness=" + Acousticness);
			sb.append(",Instrumentalness=" + Instrumentalness);
			sb.append(",Liveness=" + Liveness);
			sb.append(",Valence=" + Valence);
			sb.append(",Tempo=" + Tempo);
			sb.append(",Genre=" + Genre);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_DATAVIZ_JeuxVideo = new byte[0];
		static byte[] commonByteArray_DATAVIZ_JeuxVideo = new byte[0];

		public String Artist;

		public String getArtist() {
			return this.Artist;
		}

		public String Song;

		public String getSong() {
			return this.Song;
		}

		public String Duration_ms;

		public String getDuration_ms() {
			return this.Duration_ms;
		}

		public Boolean Explicit;

		public Boolean getExplicit() {
			return this.Explicit;
		}

		public String Year;

		public String getYear() {
			return this.Year;
		}

		public Integer Popularity;

		public Integer getPopularity() {
			return this.Popularity;
		}

		public String Danceability;

		public String getDanceability() {
			return this.Danceability;
		}

		public String Energy;

		public String getEnergy() {
			return this.Energy;
		}

		public String Key;

		public String getKey() {
			return this.Key;
		}

		public String Loudness;

		public String getLoudness() {
			return this.Loudness;
		}

		public String Mode;

		public String getMode() {
			return this.Mode;
		}

		public String Speechiness;

		public String getSpeechiness() {
			return this.Speechiness;
		}

		public String Acousticness;

		public String getAcousticness() {
			return this.Acousticness;
		}

		public String Instrumentalness;

		public String getInstrumentalness() {
			return this.Instrumentalness;
		}

		public String Liveness;

		public String getLiveness() {
			return this.Liveness;
		}

		public String Valence;

		public String getValence() {
			return this.Valence;
		}

		public String Tempo;

		public String getTempo() {
			return this.Tempo;
		}

		public String Genre;

		public String getGenre() {
			return this.Genre;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAVIZ_JeuxVideo.length) {
					if (length < 1024 && commonByteArray_DATAVIZ_JeuxVideo.length == 0) {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[1024];
					} else {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAVIZ_JeuxVideo, 0, length);
				strReturn = new String(commonByteArray_DATAVIZ_JeuxVideo, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAVIZ_JeuxVideo.length) {
					if (length < 1024 && commonByteArray_DATAVIZ_JeuxVideo.length == 0) {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[1024];
					} else {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAVIZ_JeuxVideo, 0, length);
				strReturn = new String(commonByteArray_DATAVIZ_JeuxVideo, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAVIZ_JeuxVideo) {

				try {

					int length = 0;

					this.Artist = readString(dis);

					this.Song = readString(dis);

					this.Duration_ms = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Explicit = null;
					} else {
						this.Explicit = dis.readBoolean();
					}

					this.Year = readString(dis);

					this.Popularity = readInteger(dis);

					this.Danceability = readString(dis);

					this.Energy = readString(dis);

					this.Key = readString(dis);

					this.Loudness = readString(dis);

					this.Mode = readString(dis);

					this.Speechiness = readString(dis);

					this.Acousticness = readString(dis);

					this.Instrumentalness = readString(dis);

					this.Liveness = readString(dis);

					this.Valence = readString(dis);

					this.Tempo = readString(dis);

					this.Genre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAVIZ_JeuxVideo) {

				try {

					int length = 0;

					this.Artist = readString(dis);

					this.Song = readString(dis);

					this.Duration_ms = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Explicit = null;
					} else {
						this.Explicit = dis.readBoolean();
					}

					this.Year = readString(dis);

					this.Popularity = readInteger(dis);

					this.Danceability = readString(dis);

					this.Energy = readString(dis);

					this.Key = readString(dis);

					this.Loudness = readString(dis);

					this.Mode = readString(dis);

					this.Speechiness = readString(dis);

					this.Acousticness = readString(dis);

					this.Instrumentalness = readString(dis);

					this.Liveness = readString(dis);

					this.Valence = readString(dis);

					this.Tempo = readString(dis);

					this.Genre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Artist, dos);

				// String

				writeString(this.Song, dos);

				// String

				writeString(this.Duration_ms, dos);

				// Boolean

				if (this.Explicit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.Explicit);
				}

				// String

				writeString(this.Year, dos);

				// Integer

				writeInteger(this.Popularity, dos);

				// String

				writeString(this.Danceability, dos);

				// String

				writeString(this.Energy, dos);

				// String

				writeString(this.Key, dos);

				// String

				writeString(this.Loudness, dos);

				// String

				writeString(this.Mode, dos);

				// String

				writeString(this.Speechiness, dos);

				// String

				writeString(this.Acousticness, dos);

				// String

				writeString(this.Instrumentalness, dos);

				// String

				writeString(this.Liveness, dos);

				// String

				writeString(this.Valence, dos);

				// String

				writeString(this.Tempo, dos);

				// String

				writeString(this.Genre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Artist, dos);

				// String

				writeString(this.Song, dos);

				// String

				writeString(this.Duration_ms, dos);

				// Boolean

				if (this.Explicit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.Explicit);
				}

				// String

				writeString(this.Year, dos);

				// Integer

				writeInteger(this.Popularity, dos);

				// String

				writeString(this.Danceability, dos);

				// String

				writeString(this.Energy, dos);

				// String

				writeString(this.Key, dos);

				// String

				writeString(this.Loudness, dos);

				// String

				writeString(this.Mode, dos);

				// String

				writeString(this.Speechiness, dos);

				// String

				writeString(this.Acousticness, dos);

				// String

				writeString(this.Instrumentalness, dos);

				// String

				writeString(this.Liveness, dos);

				// String

				writeString(this.Valence, dos);

				// String

				writeString(this.Tempo, dos);

				// String

				writeString(this.Genre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Artist=" + Artist);
			sb.append(",Song=" + Song);
			sb.append(",Duration_ms=" + Duration_ms);
			sb.append(",Explicit=" + String.valueOf(Explicit));
			sb.append(",Year=" + Year);
			sb.append(",Popularity=" + String.valueOf(Popularity));
			sb.append(",Danceability=" + Danceability);
			sb.append(",Energy=" + Energy);
			sb.append(",Key=" + Key);
			sb.append(",Loudness=" + Loudness);
			sb.append(",Mode=" + Mode);
			sb.append(",Speechiness=" + Speechiness);
			sb.append(",Acousticness=" + Acousticness);
			sb.append(",Instrumentalness=" + Instrumentalness);
			sb.append(",Liveness=" + Liveness);
			sb.append(",Valence=" + Valence);
			sb.append(",Tempo=" + Tempo);
			sb.append(",Genre=" + Genre);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class TestStruct implements routines.system.IPersistableRow<TestStruct> {
		final static byte[] commonByteArrayLock_DATAVIZ_JeuxVideo = new byte[0];
		static byte[] commonByteArray_DATAVIZ_JeuxVideo = new byte[0];

		public String Artist;

		public String getArtist() {
			return this.Artist;
		}

		public String Song;

		public String getSong() {
			return this.Song;
		}

		public String Duration_ms;

		public String getDuration_ms() {
			return this.Duration_ms;
		}

		public Boolean Explicit;

		public Boolean getExplicit() {
			return this.Explicit;
		}

		public String Year;

		public String getYear() {
			return this.Year;
		}

		public Integer Popularity;

		public Integer getPopularity() {
			return this.Popularity;
		}

		public String Danceability;

		public String getDanceability() {
			return this.Danceability;
		}

		public String Energy;

		public String getEnergy() {
			return this.Energy;
		}

		public String Key;

		public String getKey() {
			return this.Key;
		}

		public String Loudness;

		public String getLoudness() {
			return this.Loudness;
		}

		public String Mode;

		public String getMode() {
			return this.Mode;
		}

		public String Speechiness;

		public String getSpeechiness() {
			return this.Speechiness;
		}

		public String Acousticness;

		public String getAcousticness() {
			return this.Acousticness;
		}

		public String Instrumentalness;

		public String getInstrumentalness() {
			return this.Instrumentalness;
		}

		public String Liveness;

		public String getLiveness() {
			return this.Liveness;
		}

		public String Valence;

		public String getValence() {
			return this.Valence;
		}

		public String Tempo;

		public String getTempo() {
			return this.Tempo;
		}

		public String Genre;

		public String getGenre() {
			return this.Genre;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAVIZ_JeuxVideo.length) {
					if (length < 1024 && commonByteArray_DATAVIZ_JeuxVideo.length == 0) {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[1024];
					} else {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAVIZ_JeuxVideo, 0, length);
				strReturn = new String(commonByteArray_DATAVIZ_JeuxVideo, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAVIZ_JeuxVideo.length) {
					if (length < 1024 && commonByteArray_DATAVIZ_JeuxVideo.length == 0) {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[1024];
					} else {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAVIZ_JeuxVideo, 0, length);
				strReturn = new String(commonByteArray_DATAVIZ_JeuxVideo, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAVIZ_JeuxVideo) {

				try {

					int length = 0;

					this.Artist = readString(dis);

					this.Song = readString(dis);

					this.Duration_ms = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Explicit = null;
					} else {
						this.Explicit = dis.readBoolean();
					}

					this.Year = readString(dis);

					this.Popularity = readInteger(dis);

					this.Danceability = readString(dis);

					this.Energy = readString(dis);

					this.Key = readString(dis);

					this.Loudness = readString(dis);

					this.Mode = readString(dis);

					this.Speechiness = readString(dis);

					this.Acousticness = readString(dis);

					this.Instrumentalness = readString(dis);

					this.Liveness = readString(dis);

					this.Valence = readString(dis);

					this.Tempo = readString(dis);

					this.Genre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAVIZ_JeuxVideo) {

				try {

					int length = 0;

					this.Artist = readString(dis);

					this.Song = readString(dis);

					this.Duration_ms = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Explicit = null;
					} else {
						this.Explicit = dis.readBoolean();
					}

					this.Year = readString(dis);

					this.Popularity = readInteger(dis);

					this.Danceability = readString(dis);

					this.Energy = readString(dis);

					this.Key = readString(dis);

					this.Loudness = readString(dis);

					this.Mode = readString(dis);

					this.Speechiness = readString(dis);

					this.Acousticness = readString(dis);

					this.Instrumentalness = readString(dis);

					this.Liveness = readString(dis);

					this.Valence = readString(dis);

					this.Tempo = readString(dis);

					this.Genre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Artist, dos);

				// String

				writeString(this.Song, dos);

				// String

				writeString(this.Duration_ms, dos);

				// Boolean

				if (this.Explicit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.Explicit);
				}

				// String

				writeString(this.Year, dos);

				// Integer

				writeInteger(this.Popularity, dos);

				// String

				writeString(this.Danceability, dos);

				// String

				writeString(this.Energy, dos);

				// String

				writeString(this.Key, dos);

				// String

				writeString(this.Loudness, dos);

				// String

				writeString(this.Mode, dos);

				// String

				writeString(this.Speechiness, dos);

				// String

				writeString(this.Acousticness, dos);

				// String

				writeString(this.Instrumentalness, dos);

				// String

				writeString(this.Liveness, dos);

				// String

				writeString(this.Valence, dos);

				// String

				writeString(this.Tempo, dos);

				// String

				writeString(this.Genre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Artist, dos);

				// String

				writeString(this.Song, dos);

				// String

				writeString(this.Duration_ms, dos);

				// Boolean

				if (this.Explicit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.Explicit);
				}

				// String

				writeString(this.Year, dos);

				// Integer

				writeInteger(this.Popularity, dos);

				// String

				writeString(this.Danceability, dos);

				// String

				writeString(this.Energy, dos);

				// String

				writeString(this.Key, dos);

				// String

				writeString(this.Loudness, dos);

				// String

				writeString(this.Mode, dos);

				// String

				writeString(this.Speechiness, dos);

				// String

				writeString(this.Acousticness, dos);

				// String

				writeString(this.Instrumentalness, dos);

				// String

				writeString(this.Liveness, dos);

				// String

				writeString(this.Valence, dos);

				// String

				writeString(this.Tempo, dos);

				// String

				writeString(this.Genre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Artist=" + Artist);
			sb.append(",Song=" + Song);
			sb.append(",Duration_ms=" + Duration_ms);
			sb.append(",Explicit=" + String.valueOf(Explicit));
			sb.append(",Year=" + Year);
			sb.append(",Popularity=" + String.valueOf(Popularity));
			sb.append(",Danceability=" + Danceability);
			sb.append(",Energy=" + Energy);
			sb.append(",Key=" + Key);
			sb.append(",Loudness=" + Loudness);
			sb.append(",Mode=" + Mode);
			sb.append(",Speechiness=" + Speechiness);
			sb.append(",Acousticness=" + Acousticness);
			sb.append(",Instrumentalness=" + Instrumentalness);
			sb.append(",Liveness=" + Liveness);
			sb.append(",Valence=" + Valence);
			sb.append(",Tempo=" + Tempo);
			sb.append(",Genre=" + Genre);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(TestStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_DATAVIZ_JeuxVideo = new byte[0];
		static byte[] commonByteArray_DATAVIZ_JeuxVideo = new byte[0];

		public String Artist;

		public String getArtist() {
			return this.Artist;
		}

		public String Song;

		public String getSong() {
			return this.Song;
		}

		public String Duration_ms;

		public String getDuration_ms() {
			return this.Duration_ms;
		}

		public Boolean Explicit;

		public Boolean getExplicit() {
			return this.Explicit;
		}

		public String Year;

		public String getYear() {
			return this.Year;
		}

		public Integer Popularity;

		public Integer getPopularity() {
			return this.Popularity;
		}

		public String Danceability;

		public String getDanceability() {
			return this.Danceability;
		}

		public String Energy;

		public String getEnergy() {
			return this.Energy;
		}

		public String Key;

		public String getKey() {
			return this.Key;
		}

		public String Loudness;

		public String getLoudness() {
			return this.Loudness;
		}

		public String Mode;

		public String getMode() {
			return this.Mode;
		}

		public String Speechiness;

		public String getSpeechiness() {
			return this.Speechiness;
		}

		public String Acousticness;

		public String getAcousticness() {
			return this.Acousticness;
		}

		public String Instrumentalness;

		public String getInstrumentalness() {
			return this.Instrumentalness;
		}

		public String Liveness;

		public String getLiveness() {
			return this.Liveness;
		}

		public String Valence;

		public String getValence() {
			return this.Valence;
		}

		public String Tempo;

		public String getTempo() {
			return this.Tempo;
		}

		public String Genre;

		public String getGenre() {
			return this.Genre;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAVIZ_JeuxVideo.length) {
					if (length < 1024 && commonByteArray_DATAVIZ_JeuxVideo.length == 0) {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[1024];
					} else {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAVIZ_JeuxVideo, 0, length);
				strReturn = new String(commonByteArray_DATAVIZ_JeuxVideo, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAVIZ_JeuxVideo.length) {
					if (length < 1024 && commonByteArray_DATAVIZ_JeuxVideo.length == 0) {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[1024];
					} else {
						commonByteArray_DATAVIZ_JeuxVideo = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAVIZ_JeuxVideo, 0, length);
				strReturn = new String(commonByteArray_DATAVIZ_JeuxVideo, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAVIZ_JeuxVideo) {

				try {

					int length = 0;

					this.Artist = readString(dis);

					this.Song = readString(dis);

					this.Duration_ms = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Explicit = null;
					} else {
						this.Explicit = dis.readBoolean();
					}

					this.Year = readString(dis);

					this.Popularity = readInteger(dis);

					this.Danceability = readString(dis);

					this.Energy = readString(dis);

					this.Key = readString(dis);

					this.Loudness = readString(dis);

					this.Mode = readString(dis);

					this.Speechiness = readString(dis);

					this.Acousticness = readString(dis);

					this.Instrumentalness = readString(dis);

					this.Liveness = readString(dis);

					this.Valence = readString(dis);

					this.Tempo = readString(dis);

					this.Genre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAVIZ_JeuxVideo) {

				try {

					int length = 0;

					this.Artist = readString(dis);

					this.Song = readString(dis);

					this.Duration_ms = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Explicit = null;
					} else {
						this.Explicit = dis.readBoolean();
					}

					this.Year = readString(dis);

					this.Popularity = readInteger(dis);

					this.Danceability = readString(dis);

					this.Energy = readString(dis);

					this.Key = readString(dis);

					this.Loudness = readString(dis);

					this.Mode = readString(dis);

					this.Speechiness = readString(dis);

					this.Acousticness = readString(dis);

					this.Instrumentalness = readString(dis);

					this.Liveness = readString(dis);

					this.Valence = readString(dis);

					this.Tempo = readString(dis);

					this.Genre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Artist, dos);

				// String

				writeString(this.Song, dos);

				// String

				writeString(this.Duration_ms, dos);

				// Boolean

				if (this.Explicit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.Explicit);
				}

				// String

				writeString(this.Year, dos);

				// Integer

				writeInteger(this.Popularity, dos);

				// String

				writeString(this.Danceability, dos);

				// String

				writeString(this.Energy, dos);

				// String

				writeString(this.Key, dos);

				// String

				writeString(this.Loudness, dos);

				// String

				writeString(this.Mode, dos);

				// String

				writeString(this.Speechiness, dos);

				// String

				writeString(this.Acousticness, dos);

				// String

				writeString(this.Instrumentalness, dos);

				// String

				writeString(this.Liveness, dos);

				// String

				writeString(this.Valence, dos);

				// String

				writeString(this.Tempo, dos);

				// String

				writeString(this.Genre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Artist, dos);

				// String

				writeString(this.Song, dos);

				// String

				writeString(this.Duration_ms, dos);

				// Boolean

				if (this.Explicit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.Explicit);
				}

				// String

				writeString(this.Year, dos);

				// Integer

				writeInteger(this.Popularity, dos);

				// String

				writeString(this.Danceability, dos);

				// String

				writeString(this.Energy, dos);

				// String

				writeString(this.Key, dos);

				// String

				writeString(this.Loudness, dos);

				// String

				writeString(this.Mode, dos);

				// String

				writeString(this.Speechiness, dos);

				// String

				writeString(this.Acousticness, dos);

				// String

				writeString(this.Instrumentalness, dos);

				// String

				writeString(this.Liveness, dos);

				// String

				writeString(this.Valence, dos);

				// String

				writeString(this.Tempo, dos);

				// String

				writeString(this.Genre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Artist=" + Artist);
			sb.append(",Song=" + Song);
			sb.append(",Duration_ms=" + Duration_ms);
			sb.append(",Explicit=" + String.valueOf(Explicit));
			sb.append(",Year=" + Year);
			sb.append(",Popularity=" + String.valueOf(Popularity));
			sb.append(",Danceability=" + Danceability);
			sb.append(",Energy=" + Energy);
			sb.append(",Key=" + Key);
			sb.append(",Loudness=" + Loudness);
			sb.append(",Mode=" + Mode);
			sb.append(",Speechiness=" + Speechiness);
			sb.append(",Acousticness=" + Acousticness);
			sb.append(",Instrumentalness=" + Instrumentalness);
			sb.append(",Liveness=" + Liveness);
			sb.append(",Valence=" + Valence);
			sb.append(",Tempo=" + Tempo);
			sb.append(",Genre=" + Genre);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();
				TestStruct Test = new TestStruct();
				TestStruct row2 = Test;
				row3Struct row3 = new row3Struct();
				row4Struct row4 = new row4Struct();

				/**
				 * [tFileOutputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_1", false);
				start_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
				}

				int tos_count_tFileOutputDelimited_1 = 0;

				String fileName_tFileOutputDelimited_1 = "";
				fileName_tFileOutputDelimited_1 = (new java.io.File(
						"C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAVIZ/_output/Spotify.csv"))
								.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_1 = null;
				String extension_tFileOutputDelimited_1 = null;
				String directory_tFileOutputDelimited_1 = null;
				if ((fileName_tFileOutputDelimited_1.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") < fileName_tFileOutputDelimited_1
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
							fileName_tFileOutputDelimited_1.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					}
					directory_tFileOutputDelimited_1 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_1 = true;
				java.io.File filetFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);
				int nb_line_tFileOutputDelimited_1 = 0;
				int splitedFileNo_tFileOutputDelimited_1 = 0;
				int currentRow_tFileOutputDelimited_1 = 0;

				final String OUT_DELIM_tFileOutputDelimited_1 = /** Start field tFileOutputDelimited_1:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_1:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_1 = /**
																		 * Start field
																		 * tFileOutputDelimited_1:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_1:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_1 != null && directory_tFileOutputDelimited_1.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_1 = new java.io.File(directory_tFileOutputDelimited_1);
					if (!dir_tFileOutputDelimited_1.exists()) {
						dir_tFileOutputDelimited_1.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_1 = null;

				java.io.File fileToDelete_tFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				if (fileToDelete_tFileOutputDelimited_1.exists()) {
					fileToDelete_tFileOutputDelimited_1.delete();
				}
				outtFileOutputDelimited_1 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_1, false), "ISO-8859-15"));
				if (filetFileOutputDelimited_1.length() == 0) {
					outtFileOutputDelimited_1.write("Artist");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Song");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Duration_ms");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Explicit");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Year");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Popularity");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Danceability");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Energy");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Key");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Loudness");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Mode");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Speechiness");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Acousticness");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Instrumentalness");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Liveness");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Valence");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Tempo");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Genre");
					outtFileOutputDelimited_1.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_1", outtFileOutputDelimited_1);
				resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

				/**
				 * [tFileOutputDelimited_1 begin ] stop
				 */

				/**
				 * [tFileOutputJSON_1 begin ] start
				 */

				ok_Hash.put("tFileOutputJSON_1", false);
				start_Hash.put("tFileOutputJSON_1", System.currentTimeMillis());

				currentComponent = "tFileOutputJSON_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tFileOutputJSON_1 = 0;

				int nb_line_tFileOutputJSON_1 = 0;
				java.io.File file_tFileOutputJSON_1 = new java.io.File(
						"C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAVIZ/_output/Spotify.json");
				java.io.File dir_tFileOutputJSON_1 = file_tFileOutputJSON_1.getParentFile();
				if (dir_tFileOutputJSON_1 != null && !dir_tFileOutputJSON_1.exists()) {
					dir_tFileOutputJSON_1.mkdirs();
				}
				java.io.PrintWriter outtFileOutputJSON_1 = new java.io.PrintWriter(
						new java.io.BufferedWriter(new java.io.FileWriter(
								"C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAVIZ/_output/Spotify.json")));
				outtFileOutputJSON_1.append("{\"" + "data" + "\":[");
				boolean isFirst_tFileOutputJSON_1 = true;

				/**
				 * [tFileOutputJSON_1 begin ] stop
				 */

				/**
				 * [tReplicate_1 begin ] start
				 */

				ok_Hash.put("tReplicate_1", false);
				start_Hash.put("tReplicate_1", System.currentTimeMillis());

				currentComponent = "tReplicate_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tReplicate_1 = 0;

				/**
				 * [tReplicate_1 begin ] stop
				 */

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Test");
				}

				int tos_count_tLogRow_1 = 0;

				///////////////////////

				class Util_tLogRow_1 {

					String[] des_top = { ".", ".", "-", "+" };

					String[] des_head = { "|=", "=|", "-", "+" };

					String[] des_bottom = { "'", "'", "-", "+" };

					String name = "";

					java.util.List<String[]> list = new java.util.ArrayList<String[]>();

					int[] colLengths = new int[18];

					public void addRow(String[] row) {

						for (int i = 0; i < 18; i++) {
							if (row[i] != null) {
								colLengths[i] = Math.max(colLengths[i], row[i].length());
							}
						}
						list.add(row);
					}

					public void setTableName(String name) {

						this.name = name;
					}

					public StringBuilder format() {

						StringBuilder sb = new StringBuilder();

						sb.append(print(des_top));

						int totals = 0;
						for (int i = 0; i < colLengths.length; i++) {
							totals = totals + colLengths[i];
						}

						// name
						sb.append("|");
						int k = 0;
						for (k = 0; k < (totals + 17 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 17 - name.length() - k; i++) {
							sb.append(' ');
						}
						sb.append("|\n");

						// head and rows
						sb.append(print(des_head));
						for (int i = 0; i < list.size(); i++) {

							String[] row = list.get(i);

							java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());

							StringBuilder sbformat = new StringBuilder();
							sbformat.append("|%1$-");
							sbformat.append(colLengths[0]);
							sbformat.append("s");

							sbformat.append("|%2$-");
							sbformat.append(colLengths[1]);
							sbformat.append("s");

							sbformat.append("|%3$-");
							sbformat.append(colLengths[2]);
							sbformat.append("s");

							sbformat.append("|%4$-");
							sbformat.append(colLengths[3]);
							sbformat.append("s");

							sbformat.append("|%5$-");
							sbformat.append(colLengths[4]);
							sbformat.append("s");

							sbformat.append("|%6$-");
							sbformat.append(colLengths[5]);
							sbformat.append("s");

							sbformat.append("|%7$-");
							sbformat.append(colLengths[6]);
							sbformat.append("s");

							sbformat.append("|%8$-");
							sbformat.append(colLengths[7]);
							sbformat.append("s");

							sbformat.append("|%9$-");
							sbformat.append(colLengths[8]);
							sbformat.append("s");

							sbformat.append("|%10$-");
							sbformat.append(colLengths[9]);
							sbformat.append("s");

							sbformat.append("|%11$-");
							sbformat.append(colLengths[10]);
							sbformat.append("s");

							sbformat.append("|%12$-");
							sbformat.append(colLengths[11]);
							sbformat.append("s");

							sbformat.append("|%13$-");
							sbformat.append(colLengths[12]);
							sbformat.append("s");

							sbformat.append("|%14$-");
							sbformat.append(colLengths[13]);
							sbformat.append("s");

							sbformat.append("|%15$-");
							sbformat.append(colLengths[14]);
							sbformat.append("s");

							sbformat.append("|%16$-");
							sbformat.append(colLengths[15]);
							sbformat.append("s");

							sbformat.append("|%17$-");
							sbformat.append(colLengths[16]);
							sbformat.append("s");

							sbformat.append("|%18$-");
							sbformat.append(colLengths[17]);
							sbformat.append("s");

							sbformat.append("|\n");

							formatter.format(sbformat.toString(), (Object[]) row);

							sb.append(formatter.toString());
							if (i == 0)
								sb.append(print(des_head)); // print the head
						}

						// end
						sb.append(print(des_bottom));
						return sb;
					}

					private StringBuilder print(String[] fillChars) {
						StringBuilder sb = new StringBuilder();
						// first column
						sb.append(fillChars[0]);
						for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[2] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[3] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[4] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[5] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[6] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[7] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[8] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[9] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[10] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[11] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[12] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[13] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[14] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[15] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[16] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						// last column
						for (int i = 0; i < colLengths[17] - fillChars[1].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[1]);
						sb.append("\n");
						return sb;
					}

					public boolean isTableEmpty() {
						if (list.size() > 1)
							return false;
						return true;
					}
				}
				Util_tLogRow_1 util_tLogRow_1 = new Util_tLogRow_1();
				util_tLogRow_1.setTableName("tLogRow_1");
				util_tLogRow_1.addRow(new String[] { "Artist", "Song", "Duration_ms", "Explicit", "Year", "Popularity",
						"Danceability", "Energy", "Key", "Loudness", "Mode", "Speechiness", "Acousticness",
						"Instrumentalness", "Liveness", "Valence", "Tempo", "Genre", });
				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				TestStruct Test_tmp = new TestStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_1", false);
				start_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_1";

				int tos_count_tFileInputDelimited_1 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_1 = null;
				int limit_tFileInputDelimited_1 = -1;
				try {

					Object filename_tFileInputDelimited_1 = "C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAVIZ/_input/sonspotifyv2.csv";
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0 || random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAVIZ/_input/sonspotifyv2.csv",
								"UTF-8", ";", "\n", true, 1, 0, limit_tFileInputDelimited_1, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_1 != null && fid_tFileInputDelimited_1.nextRecord()) {
						rowstate_tFileInputDelimited_1.reset();

						row1 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row1 = new row1Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_1 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_1 = 0;

							row1.Artist = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 1;

							row1.Song = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 2;

							row1.Duration_ms = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 3;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Explicit = ParserUtils.parseTo_Boolean(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Explicit", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.Explicit = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 4;

							row1.Year = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 5;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Popularity = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Popularity", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.Popularity = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 6;

							row1.Danceability = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 7;

							row1.Energy = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 8;

							row1.Key = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 9;

							row1.Loudness = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 10;

							row1.Mode = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 11;

							row1.Speechiness = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 12;

							row1.Acousticness = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 13;

							row1.Instrumentalness = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 14;

							row1.Liveness = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 15;

							row1.Valence = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 16;

							row1.Tempo = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 17;

							row1.Genre = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							System.err.println(e.getMessage());
							row1 = null;

						}

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */

						/**
						 * [tFileInputDelimited_1 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_begin ] stop
						 */
// Start of branch "row1"
						if (row1 != null) {

							/**
							 * [tMap_1 main ] start
							 */

							currentComponent = "tMap_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row1"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_1 = false;
							boolean mainRowRejected_tMap_1 = false;

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
								// ###############################
								// # Output tables

								Test = null;

// # Output table : 'Test'
								Test_tmp.Artist = row1.Artist;
								Test_tmp.Song = row1.Song;
								Test_tmp.Duration_ms = row1.Duration_ms.replaceAll("\\.", ",");
								Test_tmp.Explicit = row1.Explicit;
								Test_tmp.Year = row1.Year;
								Test_tmp.Popularity = row1.Popularity;
								Test_tmp.Danceability = row1.Danceability.replaceAll("\\.", ",");
								Test_tmp.Energy = row1.Energy.replaceAll("\\.", ",");
								Test_tmp.Key = row1.Key.replaceAll("\\.", ",");
								Test_tmp.Loudness = row1.Loudness.replaceAll("\\.", ",");
								Test_tmp.Mode = row1.Mode.replaceAll("\\.", ",");
								Test_tmp.Speechiness = row1.Speechiness.replaceAll("\\.", ",");
								Test_tmp.Acousticness = row1.Acousticness.replaceAll("\\.", ",");
								Test_tmp.Instrumentalness = row1.Instrumentalness.replaceAll("\\.", ",");
								Test_tmp.Liveness = row1.Liveness.replaceAll("\\.", ",");
								Test_tmp.Valence = row1.Valence.replaceAll("\\.", ",");
								Test_tmp.Tempo = row1.Tempo.replaceAll("\\.", ",");
								Test_tmp.Genre = row1.Genre;
								Test = Test_tmp;
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_1 = false;

							tos_count_tMap_1++;

							/**
							 * [tMap_1 main ] stop
							 */

							/**
							 * [tMap_1 process_data_begin ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_begin ] stop
							 */
// Start of branch "Test"
							if (Test != null) {

								/**
								 * [tLogRow_1 main ] start
								 */

								currentComponent = "tLogRow_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "Test"

									);
								}

///////////////////////		

								String[] row_tLogRow_1 = new String[18];

								if (Test.Artist != null) { //
									row_tLogRow_1[0] = String.valueOf(Test.Artist);

								} //

								if (Test.Song != null) { //
									row_tLogRow_1[1] = String.valueOf(Test.Song);

								} //

								if (Test.Duration_ms != null) { //
									row_tLogRow_1[2] = String.valueOf(Test.Duration_ms);

								} //

								if (Test.Explicit != null) { //
									row_tLogRow_1[3] = String.valueOf(Test.Explicit);

								} //

								if (Test.Year != null) { //
									row_tLogRow_1[4] = String.valueOf(Test.Year);

								} //

								if (Test.Popularity != null) { //
									row_tLogRow_1[5] = String.valueOf(Test.Popularity);

								} //

								if (Test.Danceability != null) { //
									row_tLogRow_1[6] = String.valueOf(Test.Danceability);

								} //

								if (Test.Energy != null) { //
									row_tLogRow_1[7] = String.valueOf(Test.Energy);

								} //

								if (Test.Key != null) { //
									row_tLogRow_1[8] = String.valueOf(Test.Key);

								} //

								if (Test.Loudness != null) { //
									row_tLogRow_1[9] = String.valueOf(Test.Loudness);

								} //

								if (Test.Mode != null) { //
									row_tLogRow_1[10] = String.valueOf(Test.Mode);

								} //

								if (Test.Speechiness != null) { //
									row_tLogRow_1[11] = String.valueOf(Test.Speechiness);

								} //

								if (Test.Acousticness != null) { //
									row_tLogRow_1[12] = String.valueOf(Test.Acousticness);

								} //

								if (Test.Instrumentalness != null) { //
									row_tLogRow_1[13] = String.valueOf(Test.Instrumentalness);

								} //

								if (Test.Liveness != null) { //
									row_tLogRow_1[14] = String.valueOf(Test.Liveness);

								} //

								if (Test.Valence != null) { //
									row_tLogRow_1[15] = String.valueOf(Test.Valence);

								} //

								if (Test.Tempo != null) { //
									row_tLogRow_1[16] = String.valueOf(Test.Tempo);

								} //

								if (Test.Genre != null) { //
									row_tLogRow_1[17] = String.valueOf(Test.Genre);

								} //

								util_tLogRow_1.addRow(row_tLogRow_1);
								nb_line_tLogRow_1++;
//////

//////                    

///////////////////////    			

								row2 = Test;

								tos_count_tLogRow_1++;

								/**
								 * [tLogRow_1 main ] stop
								 */

								/**
								 * [tLogRow_1 process_data_begin ] start
								 */

								currentComponent = "tLogRow_1";

								/**
								 * [tLogRow_1 process_data_begin ] stop
								 */

								/**
								 * [tReplicate_1 main ] start
								 */

								currentComponent = "tReplicate_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row2"

									);
								}

								row3 = new row3Struct();

								row3.Artist = row2.Artist;
								row3.Song = row2.Song;
								row3.Duration_ms = row2.Duration_ms;
								row3.Explicit = row2.Explicit;
								row3.Year = row2.Year;
								row3.Popularity = row2.Popularity;
								row3.Danceability = row2.Danceability;
								row3.Energy = row2.Energy;
								row3.Key = row2.Key;
								row3.Loudness = row2.Loudness;
								row3.Mode = row2.Mode;
								row3.Speechiness = row2.Speechiness;
								row3.Acousticness = row2.Acousticness;
								row3.Instrumentalness = row2.Instrumentalness;
								row3.Liveness = row2.Liveness;
								row3.Valence = row2.Valence;
								row3.Tempo = row2.Tempo;
								row3.Genre = row2.Genre;
								row4 = new row4Struct();

								row4.Artist = row2.Artist;
								row4.Song = row2.Song;
								row4.Duration_ms = row2.Duration_ms;
								row4.Explicit = row2.Explicit;
								row4.Year = row2.Year;
								row4.Popularity = row2.Popularity;
								row4.Danceability = row2.Danceability;
								row4.Energy = row2.Energy;
								row4.Key = row2.Key;
								row4.Loudness = row2.Loudness;
								row4.Mode = row2.Mode;
								row4.Speechiness = row2.Speechiness;
								row4.Acousticness = row2.Acousticness;
								row4.Instrumentalness = row2.Instrumentalness;
								row4.Liveness = row2.Liveness;
								row4.Valence = row2.Valence;
								row4.Tempo = row2.Tempo;
								row4.Genre = row2.Genre;

								tos_count_tReplicate_1++;

								/**
								 * [tReplicate_1 main ] stop
								 */

								/**
								 * [tReplicate_1 process_data_begin ] start
								 */

								currentComponent = "tReplicate_1";

								/**
								 * [tReplicate_1 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_1 main ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row3"

									);
								}

								StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();
								if (row3.Artist != null) {
									sb_tFileOutputDelimited_1.append(row3.Artist);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row3.Song != null) {
									sb_tFileOutputDelimited_1.append(row3.Song);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row3.Duration_ms != null) {
									sb_tFileOutputDelimited_1.append(row3.Duration_ms);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row3.Explicit != null) {
									sb_tFileOutputDelimited_1.append(row3.Explicit);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row3.Year != null) {
									sb_tFileOutputDelimited_1.append(row3.Year);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row3.Popularity != null) {
									sb_tFileOutputDelimited_1.append(row3.Popularity);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row3.Danceability != null) {
									sb_tFileOutputDelimited_1.append(row3.Danceability);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row3.Energy != null) {
									sb_tFileOutputDelimited_1.append(row3.Energy);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row3.Key != null) {
									sb_tFileOutputDelimited_1.append(row3.Key);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row3.Loudness != null) {
									sb_tFileOutputDelimited_1.append(row3.Loudness);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row3.Mode != null) {
									sb_tFileOutputDelimited_1.append(row3.Mode);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row3.Speechiness != null) {
									sb_tFileOutputDelimited_1.append(row3.Speechiness);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row3.Acousticness != null) {
									sb_tFileOutputDelimited_1.append(row3.Acousticness);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row3.Instrumentalness != null) {
									sb_tFileOutputDelimited_1.append(row3.Instrumentalness);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row3.Liveness != null) {
									sb_tFileOutputDelimited_1.append(row3.Liveness);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row3.Valence != null) {
									sb_tFileOutputDelimited_1.append(row3.Valence);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row3.Tempo != null) {
									sb_tFileOutputDelimited_1.append(row3.Tempo);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row3.Genre != null) {
									sb_tFileOutputDelimited_1.append(row3.Genre);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);

								nb_line_tFileOutputDelimited_1++;
								resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

								outtFileOutputDelimited_1.write(sb_tFileOutputDelimited_1.toString());

								tos_count_tFileOutputDelimited_1++;

								/**
								 * [tFileOutputDelimited_1 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_1 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								/**
								 * [tFileOutputDelimited_1 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_1 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								/**
								 * [tFileOutputDelimited_1 process_data_end ] stop
								 */

								/**
								 * [tFileOutputJSON_1 main ] start
								 */

								currentComponent = "tFileOutputJSON_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row4"

									);
								}

								org.json.simple.JSONObject jsonRowtFileOutputJSON_1 = new org.json.simple.JSONObject();
								if (row4.Artist != null) {

									jsonRowtFileOutputJSON_1.put("Artist", row4.Artist);

								} else {
									jsonRowtFileOutputJSON_1.put("Artist", null);
								}

								if (row4.Song != null) {

									jsonRowtFileOutputJSON_1.put("Song", row4.Song);

								} else {
									jsonRowtFileOutputJSON_1.put("Song", null);
								}

								if (row4.Duration_ms != null) {

									jsonRowtFileOutputJSON_1.put("Duration_ms", row4.Duration_ms);

								} else {
									jsonRowtFileOutputJSON_1.put("Duration_ms", null);
								}

								if (row4.Explicit != null) {

									jsonRowtFileOutputJSON_1.put("Explicit", row4.Explicit);

								} else {
									jsonRowtFileOutputJSON_1.put("Explicit", null);
								}

								if (row4.Year != null) {

									jsonRowtFileOutputJSON_1.put("Year", row4.Year);

								} else {
									jsonRowtFileOutputJSON_1.put("Year", null);
								}

								if (row4.Popularity != null) {

									jsonRowtFileOutputJSON_1.put("Popularity", row4.Popularity);

								} else {
									jsonRowtFileOutputJSON_1.put("Popularity", null);
								}

								if (row4.Danceability != null) {

									jsonRowtFileOutputJSON_1.put("Danceability", row4.Danceability);

								} else {
									jsonRowtFileOutputJSON_1.put("Danceability", null);
								}

								if (row4.Energy != null) {

									jsonRowtFileOutputJSON_1.put("Energy", row4.Energy);

								} else {
									jsonRowtFileOutputJSON_1.put("Energy", null);
								}

								if (row4.Key != null) {

									jsonRowtFileOutputJSON_1.put("Key", row4.Key);

								} else {
									jsonRowtFileOutputJSON_1.put("Key", null);
								}

								if (row4.Loudness != null) {

									jsonRowtFileOutputJSON_1.put("Loudness", row4.Loudness);

								} else {
									jsonRowtFileOutputJSON_1.put("Loudness", null);
								}

								if (row4.Mode != null) {

									jsonRowtFileOutputJSON_1.put("Mode", row4.Mode);

								} else {
									jsonRowtFileOutputJSON_1.put("Mode", null);
								}

								if (row4.Speechiness != null) {

									jsonRowtFileOutputJSON_1.put("Speechiness", row4.Speechiness);

								} else {
									jsonRowtFileOutputJSON_1.put("Speechiness", null);
								}

								if (row4.Acousticness != null) {

									jsonRowtFileOutputJSON_1.put("Acousticness", row4.Acousticness);

								} else {
									jsonRowtFileOutputJSON_1.put("Acousticness", null);
								}

								if (row4.Instrumentalness != null) {

									jsonRowtFileOutputJSON_1.put("Instrumentalness", row4.Instrumentalness);

								} else {
									jsonRowtFileOutputJSON_1.put("Instrumentalness", null);
								}

								if (row4.Liveness != null) {

									jsonRowtFileOutputJSON_1.put("Liveness", row4.Liveness);

								} else {
									jsonRowtFileOutputJSON_1.put("Liveness", null);
								}

								if (row4.Valence != null) {

									jsonRowtFileOutputJSON_1.put("Valence", row4.Valence);

								} else {
									jsonRowtFileOutputJSON_1.put("Valence", null);
								}

								if (row4.Tempo != null) {

									jsonRowtFileOutputJSON_1.put("Tempo", row4.Tempo);

								} else {
									jsonRowtFileOutputJSON_1.put("Tempo", null);
								}

								if (row4.Genre != null) {

									jsonRowtFileOutputJSON_1.put("Genre", row4.Genre);

								} else {
									jsonRowtFileOutputJSON_1.put("Genre", null);
								}

								if (!isFirst_tFileOutputJSON_1) {
									outtFileOutputJSON_1.append(",");
								}
								isFirst_tFileOutputJSON_1 = false;
								outtFileOutputJSON_1.append(jsonRowtFileOutputJSON_1.toJSONString());
								nb_line_tFileOutputJSON_1++;

								tos_count_tFileOutputJSON_1++;

								/**
								 * [tFileOutputJSON_1 main ] stop
								 */

								/**
								 * [tFileOutputJSON_1 process_data_begin ] start
								 */

								currentComponent = "tFileOutputJSON_1";

								/**
								 * [tFileOutputJSON_1 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputJSON_1 process_data_end ] start
								 */

								currentComponent = "tFileOutputJSON_1";

								/**
								 * [tFileOutputJSON_1 process_data_end ] stop
								 */

								/**
								 * [tReplicate_1 process_data_end ] start
								 */

								currentComponent = "tReplicate_1";

								/**
								 * [tReplicate_1 process_data_end ] stop
								 */

								/**
								 * [tLogRow_1 process_data_end ] start
								 */

								currentComponent = "tLogRow_1";

								/**
								 * [tLogRow_1 process_data_end ] stop
								 */

							} // End of branch "Test"

							/**
							 * [tMap_1 process_data_end ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_end ] stop
							 */

						} // End of branch "row1"

						/**
						 * [tFileInputDelimited_1 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

					}
				} finally {
					if (!((Object) ("C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAVIZ/_input/sonspotifyv2.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_1 != null) {
							fid_tFileInputDelimited_1.close();
						}
					}
					if (fid_tFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE", fid_tFileInputDelimited_1.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

//////

				java.io.PrintStream consoleOut_tLogRow_1 = null;
				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_1 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
				}

				consoleOut_tLogRow_1.println(util_tLogRow_1.format().toString());
				consoleOut_tLogRow_1.flush();
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Test");
				}

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
				 */

				/**
				 * [tReplicate_1 end ] start
				 */

				currentComponent = "tReplicate_1";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tReplicate_1", true);
				end_Hash.put("tReplicate_1", System.currentTimeMillis());

				/**
				 * [tReplicate_1 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 end ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (outtFileOutputDelimited_1 != null) {
					outtFileOutputDelimited_1.flush();
					outtFileOutputDelimited_1.close();
				}

				globalMap.put("tFileOutputDelimited_1_NB_LINE", nb_line_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);

				resourceMap.put("finish_tFileOutputDelimited_1", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
				}

				ok_Hash.put("tFileOutputDelimited_1", true);
				end_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_1 end ] stop
				 */

				/**
				 * [tFileOutputJSON_1 end ] start
				 */

				currentComponent = "tFileOutputJSON_1";

				outtFileOutputJSON_1.print("]}");
				outtFileOutputJSON_1.close();
				globalMap.put("tFileOutputJSON_1_NB_LINE", nb_line_tFileOutputJSON_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tFileOutputJSON_1", true);
				end_Hash.put("tFileOutputJSON_1", System.currentTimeMillis());

				/**
				 * [tFileOutputJSON_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				currentComponent = "tFileInputDelimited_1";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
				 */

				/**
				 * [tReplicate_1 finally ] start
				 */

				currentComponent = "tReplicate_1";

				/**
				 * [tReplicate_1 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (resourceMap.get("finish_tFileOutputDelimited_1") == null) {

					java.io.Writer outtFileOutputDelimited_1 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_1");
					if (outtFileOutputDelimited_1 != null) {
						outtFileOutputDelimited_1.flush();
						outtFileOutputDelimited_1.close();
					}

				}

				/**
				 * [tFileOutputDelimited_1 finally ] stop
				 */

				/**
				 * [tFileOutputJSON_1 finally ] start
				 */

				currentComponent = "tFileOutputJSON_1";

				/**
				 * [tFileOutputJSON_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final JeuxVideo JeuxVideoClass = new JeuxVideo();

		int exitCode = JeuxVideoClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = JeuxVideo.class.getClassLoader()
					.getResourceAsStream("dataviz/jeuxvideo_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = JeuxVideo.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputDelimited_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_1) {
			globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : JeuxVideo");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 146831 characters generated by Talend Open Studio for Data Integration on the
 * 5 février 2024, 10:41:28 CET
 ************************************************************************************************/