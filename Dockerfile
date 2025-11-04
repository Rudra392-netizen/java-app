# Use supported OpenJDK 17 image
FROM openjdk:17-slim

# Metadata
LABEL maintainer="trainwithshubham@gmail.com"
LABEL version="1.0"
LABEL description="A Java Quotes application"

# Set working directory
WORKDIR /app

# Copy source code and other files
COPY src/ /app/src/
COPY quotes.txt /app/

# Compile Java files
WORKDIR /app/src
RUN javac Main.java

# Expose port
EXPOSE 8000

# Run the Java application
CMD ["java", "Main"]
