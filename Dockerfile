
# Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
# Click nbfs://nbhost/SystemFileSystem/Templates/Other/Dockerfile to edit this template

FROM alpine:latest
copy target/ExaDataEngAssesment-0.0.1-SNAPSHOT .
CMD ["java","-jar","ExaDataEngAssesment-0.0.1-SNAPSHOT"]
