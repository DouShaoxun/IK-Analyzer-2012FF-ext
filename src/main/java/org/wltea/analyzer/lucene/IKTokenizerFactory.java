package org.wltea.analyzer.lucene;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeFactory;

public class IKTokenizerFactory extends TokenizerFactory {
    private boolean useSmart;
    private InputStream inputStream;
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public boolean useSmart() {
        return this.useSmart;
    }

    public void setUseSmart(boolean useSmart) {
        this.useSmart = useSmart;
    }

    public IKTokenizerFactory(Map<String, String> args) {
        super(args);
        String useSmartArg = args.get("useSmart");
        this.setUseSmart(useSmartArg != null ? Boolean.parseBoolean(useSmartArg) : false);
    }

    @Override
    public Tokenizer create(AttributeFactory factory) {
        Tokenizer _IKTokenizer = null;
        _IKTokenizer = new IKTokenizer(factory, this.useSmart,this.filePath);
        return _IKTokenizer;
    }
}
