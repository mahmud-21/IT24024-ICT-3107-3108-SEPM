# Bundled runtime libraries

The lib/ folder contains unmodified official Maven Central JARs. Each JAR retains its embedded license/notice metadata.

- Eclipse Jetty 12.0.16: Eclipse Public License 2.0 / Apache License 2.0.
- Jakarta Servlet API 6.0.0: Eclipse Public License 2.0 / GPLv2 with Classpath Exception.
- H2 2.3.232: Mozilla Public License 2.0 / Eclipse Public License 1.0.
- SLF4J 2.0.16: MIT License.

Maven source builds obtain their dependencies using pom.xml. The bundled JAR was compiled from the included source, targeting Java 17. Tests were excluded from that runtime JAR.
