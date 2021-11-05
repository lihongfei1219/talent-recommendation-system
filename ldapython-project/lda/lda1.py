# -*- codeing = utf-8 -*-
# @Time : 2021/11/4 16:45
# @Auther : 李宏飞
# @File : lda1.py
# @Software : PyCharm
import time
import numpy as np
from gensim import corpora, models, similarities
import jieba


def load_stopword():
    f_stop = open('stopwords.txt', encoding='utf-8')
    sw = [line.strip() for line in f_stop]
    f_stop.close()
    return sw


if __name__ == '__main__':
    print('初始化停止词')
    t_start = time.time()
    stop_words = load_stopword()
    print('开始读入语料数据 -- ')
    f = open('data1.txt', encoding='utf-8')
    fl = []
    for line in f:
        ff = jieba.cut(line, cut_all=False)
        fl.append(" ".join(ff))

    texts = [[word for word in line.strip().lower().split(" ") if word not in stop_words] for line in fl]
    print('读入预料数据完成，用%.10f秒' % (time.time() - t_start))
    f.close()
    M = len(texts)
    print('文本数目', M, '个')
    print(texts)

    print('正在建立词典 --')
    dictonary = corpora.Dictionary(texts)
    V = len(dictonary)
    print('正在计算文本向量 --')
    corpus = [dictonary.doc2bow(text) for text in texts]
    print('正在计算文档TF-IDF --')
    t_start = time.time()
    corpus_tfidf = models.TfidfModel(corpus)[corpus]
    print('建立文档TF-IDF完成，用时%.3f秒' % (time.time() - t_start))
    print(u'词的个数：', V)

    print('LDA模型你和推断 --')
    num_topics = 10
    t_start = time.time()
    ida = models.LdaModel(corpus_tfidf, num_topics=num_topics, id2word=dictonary,
                          alpha=0.01, eta=0.01, minimum_probability=0.001,
                          update_every=1, chunksize=100, passes=1)
    print('LDA模型完成，训练时间为\t%.3f秒' % (time.time() - t_start))

    num_show_topic = 10
    print('10个文档的主题分布')
    doc_topics = ida.get_document_topics(corpus_tfidf)
    print(doc_topics)
    idx = np.arange(M)
    np.random.shuffle(idx)
    idx = idx[:10]
    for i in idx:
        topic = np.array(doc_topics[i])

        print('topic = \t', topic)
        topic_distribute = np.array(topic[:, 1])
        topic_idx = topic_distribute.argsort()[:-num_show_topic - 1:-1]

        print(('第%d个文档的前%d个主题' % (i, num_show_topic)), topic_idx)
        print(topic_distribute[topic_idx])
    num_show_term = 5
    print('每个主题的词分布')
    for topic_id in range(num_topics):
        print("主题#%d:\t" % topic_id)
        term_distribute_all = ida.get_topic_terms(topicid=topic_id)
        term_distribute = term_distribute_all[:num_show_term]
        term_distribute = np.array(term_distribute)
        term_id = term_distribute[:, 0].astype(np.int)
        print('词:\t')
        for t in term_id:
            print(dictonary.id2token[t])

    similarity=similarities.MatrixSimilarity(ida[corpus_tfidf])
    print(list(similarity))
