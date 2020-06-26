# Steep OpenFaas example function

This is an example for a <a href="https://steep-wms.github.io">Steep</a> OpenFaas function.


## Getting started
1. Start a Kubernetes Cluster.
2. Install OpenFaas. You can have a look into the OpenFaas documentation or just use something like this:
    ```
    kubectl apply -f https://raw.githubusercontent.com/openfaas/faas-netes/master/namespaces.yml
    
    helm repo update \
    && helm upgrade openfaas --install openfaas/openfaas \
    --namespace openfaas  \
    --set functionNamespace=openfaas-fn \
    --set generateBasicAuth=true \
    --set gateway.scaleFromZero=true \
    --set gateway.readTimeout=10m \
    --set gateway.writeTimeout=10m \
    --set gateway.upstreamTimeout=9m \
    --set faasIdler.dryRun=false \
    --set faasIdler.inactivityDuration=1m \
    --set faasIdler.reconcileInterval=1m
    ```
3. If you cannot access the OpenFaas gateway, add a port mapping.
    ```
    export OPENFAAS_URL=http://127.0.0.1:31112
    kubectl port-forward svc/gateway -n openfaas 31112:8080
    ```
4. Clone and deploy the function
    ```
   export OPENFAAS_URL=http://127.0.0.1:31112
   PASSWORD=$(kubectl -n openfaas get secret basic-auth -o jsonpath="{.data.basic-auth-password}" | base64 --decode)
   faas-cli login --username admin --gateway 127.0.0.1:31112 --password $PASSWORD
   
    git clone git@github.com:hwuerz/steep-openfaas-example.git
    cd steep-openfaas-example
    faas-cli up -f example-function.yml
    ```
5. Call the function with a dummy payload
    ```
   curl -X POST -i 'http://127.0.0.1:31112/function/example-function' --data '{"id":"My example","path":"example-function","arguments":[],"runtime":"faas","runtimeArgs":[]}'
   ```
   You will see something like
   ```
   HTTP/1.1 200 OK
   Content-Length: 60
   Content-Type: text/plain; charset=utf-8
   Date: Fri, 26 Jun 2020 15:42:27 GMT
   X-Duration-Seconds: 0.001324
   
   Hello, world! I am an example for a steep openfaas function.
   ```

## Run the function from Steep
WIP
