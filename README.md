## onplan
    mvn clean install
    mvn spring-boot:run

## To build, tag docker image and push it to ECR
    sudo docker build -t onplan_v1 .
    sudo docker image ls
    sudo docker run -p 8080:8080 onplan_v1


## To enable disyributed tracing locally, follow below steps:

### Run xray demon locally
    step 1: https://docs.amazonaws.cn/en_us/xray/latest/devguide/xray-daemon-local.html

    step 2: put @XRayEnabled in components to be traced


## How to view metrics on prometheus and K8 dashboard on local host
    1. Install kubectl on local machine
    2. Open terminal and export aws key and credentials
        export AWS_ACCESS_KEY_ID=xxxxxxxxxxxxxxxxx
        export AWS_SECRET_ACCESS_KEY=xxxxxxxxxxxxxxxx
        export AWS_DEFAULT_REGION=<region>
    3. Check cluster status  : aws eks --region us-west-2 describe-cluster --name OnPlan-EKS --query cluster.status 
    4. get cluster configurations on loca host:  aws eks --region us-west-2 update-kubeconfig --name OnPlan-EKS
    5. To check pods status: kubectl get pods --all-namespaces 
    6. To get the token , to view K8 dashboard: kubectl -n kube-system describe secret $(kubectl -n kube-system get secret | grep eks-admin | awk '{print $1}')
    7. Copy token from 7, run kubectl proxy , then open http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/#/login in browser. Paste token. 
    8. To view cluster metrics,
        8.1 Check for prometheus pods, kubectl get pods -n prometheus
        8.2 Do port forwarding, kubectl --namespace=prometheus port-forward deploy/prometheus-server 9090
        8.3 Open http://localhost:9090/graph on browser and search for metrics
    9. To access services , open url http://<ec2-public-ip>:31475/ping on browser
    