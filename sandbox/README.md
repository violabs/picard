```
  containers:
    - name: postgres
      image: postgres:14
      ports:
        - containerPort: 5432
      envFrom:
        - configMapRef:
            name: postgres-config
        - secretRef:
            name: postgres-secret
      volumeMounts:
        - mountPath: /var/lib/postgresql/data
          name: postgres-storage
```


```aiignore
# Delete the Deployment
kubectl delete deployment postgres -n postgres

# Delete the Service
kubectl delete service postgres -n postgres

# Delete the PVC
kubectl delete pvc postgres-pvc -n postgres

# Delete the ConfigMap
kubectl delete configmap postgres-config -n postgres

# Delete the Secret
kubectl delete secret postgres-secret -n postgres
```

kubectl delete -f sandbox/src/main/resources/generated/postgres.yaml

kubectl delete all,configmap,secret,pvc -l app=postgres -n postgres

kubectl port-forward -n postgres service/postgres 5432:5432
